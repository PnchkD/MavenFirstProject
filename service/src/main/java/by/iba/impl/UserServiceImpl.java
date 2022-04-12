package by.iba.impl;

import by.iba.UserRolesService;
import by.iba.UserService;
import by.iba.dto.req.*;
import by.iba.dto.resp.UserDTO;
import by.iba.dto.resp.UserRoleDTO;
import by.iba.entity.user.Photo;
import by.iba.entity.user.UserEntity;
import by.iba.entity.user.UserRole;
import by.iba.exception.*;
import by.iba.mapper.UserMapper;
import by.iba.repository.UserRepository;
import by.iba.repository.UserRolesRepository;
import by.iba.service.EmailService;
import by.iba.specification.user.UserSpecificationsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final EmailService emailService;

    private final ObjectMapper objectMapper;
    private final UserRolesService userRolesService;
    private final UserRolesRepository userRolesRepository;

    private static final String STANDARD_ROLE ="USER";

    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<UserDTO> findAll(UserSortCriteriaReqDTO userSortCriteriaReqDTO) {
        checkDefaultValues(userSortCriteriaReqDTO);

        Pageable paging = PageRequest.of(userSortCriteriaReqDTO.getPageNo(), userSortCriteriaReqDTO.getPageSize(), Sort.by(userSortCriteriaReqDTO.getSortBy()));
        if(userSortCriteriaReqDTO.isDesc()) {
            paging = PageRequest.of(userSortCriteriaReqDTO.getPageNo(), userSortCriteriaReqDTO.getPageSize(), Sort.by(userSortCriteriaReqDTO.getSortBy()).descending());
        }

        Page<UserEntity> pagedResult = userRepository.findAll(paging);

        return pagedResult.getContent().stream()
                .map(userMapper::fillFromInDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserDTO> searchUser(String search) {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();

        if (Objects.nonNull(search)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");

            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }

        }

        Specification<UserEntity> specification = builder.build();

        return userRepository.findAll(specification).stream()
                .map(userMapper::fillFromInDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public UserDTO save(UserReqDTO userReqDTO) {

        UserEntity user = objectMapper.convertValue(userReqDTO, UserEntity.class);
        user.setAvatar(new Photo(userReqDTO.getImage()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(userRolesService.findByName(STANDARD_ROLE));

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserIsAlreadyExistException();
        }

        userRepository.save(user);
        try {
            emailService.sendEmail(user.getEmail(), "Welcome!", "Your registration is successful!");
        } catch (EmailServiceException ex) {
            throw new InvalidEmailException("This email does not exist");
        }

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public UserEntity login(UserAuthReqDTO userAuthReqDTO) {

        UserEntity user = userRepository.findByLogin(userAuthReqDTO.getLogin())
                .orElseThrow(() -> new BadCredentialsException("BAD_CREDENTIALS"));

        if (Objects.nonNull(user.getBannedDate())) {
            throw new UserHasBeenBannedException("User account is disabled.");
        }

        user.setDateOfLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserPersonalDataReqDTO userPersonalDataReqDTO) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userPersonalDataReqDTO);

        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public UserDTO banUser(Long id, boolean verdict) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(verdict) {

            if(user.getRoles().contains(userRolesService.findByName("ADMIN"))) {
                throw new InvalidBanException();
            }

            user.setBannedDate(LocalDateTime.now());
            userRepository.save(user);

        } else {
            user.setBannedDate(null);
            userRepository.save(user);
        }

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public void confirmUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setDateOfApproved(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UserCredentialsReqDTO userChangeCredentialsReqDTO) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(Objects.isNull(userChangeCredentialsReqDTO.getLogin())
                || Objects.isNull(userChangeCredentialsReqDTO.getNewPassword())
                || Objects.isNull(userChangeCredentialsReqDTO.getOldPassword())
                || Objects.isNull(userChangeCredentialsReqDTO.getConfirmedPassword())
                || (user.getPassword()).equals(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getOldPassword()))
                || !userChangeCredentialsReqDTO.getNewPassword().equals(userChangeCredentialsReqDTO.getConfirmedPassword())) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void updateAvatar(Long id, UserAvatarReqDTO userAvatarReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userAvatarReqDTO);

        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (userRolesReqDTO.getRoles().isEmpty()) {
           throw new UserRoleNotFoundException();
        }

        Set<UserRole> roles = new HashSet<>();

        for(UserRoleDTO role : userRolesReqDTO.getRoles()) {
            UserRole userRole = userRolesRepository.findByName(role.getRole())
                    .orElseThrow(UserRoleNotFoundException::new);
            roles.add(userRole);
        }

        user.setRoles(roles);
        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO) {
        UserEntity user = userRepository.findByLogin(userPasswordRecoveryReqDTO.getLogin())
                .orElseThrow(UserNotFoundException::new);

        if(!userPasswordRecoveryReqDTO.getNewPassword().equals(userPasswordRecoveryReqDTO.getConfirmedPassword())
                && userPasswordRecoveryReqDTO.getNewPassword() != null){
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userPasswordRecoveryReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);

    }

    private void checkDefaultValues(UserSortCriteriaReqDTO userSortCriteriaReqDTO) {
        if(Objects.isNull(userSortCriteriaReqDTO.getPageNo())) {
            userSortCriteriaReqDTO.setPageNo(0);
        }

        if(Objects.isNull(userSortCriteriaReqDTO.getPageSize())) {
            userSortCriteriaReqDTO.setPageSize(10);
        }

        if(Objects.isNull(userSortCriteriaReqDTO.getSortBy())) {
            userSortCriteriaReqDTO.setSortBy("id");
        }
    }

}
