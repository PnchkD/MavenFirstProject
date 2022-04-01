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
import by.iba.service.impl.DefaultEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final DefaultEmailService emailService;
    private final ObjectMapper objectMapper;
    private final UserRolesService userRolesService;
    private final UserRolesRepository userRolesRepository;

    private static final String STANDARD_ROLE ="USER";

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
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
    public UserDTO save(UserEntity user) {
        if (Objects.isNull(user.getId())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.getRoles().add(new UserRole());
            user.setDateOfCreation(LocalDateTime.now());
        }

        user.setDateOfLastUpdate(LocalDateTime.now());
        userRepository.save(user);

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public boolean registerUser(UserReqDTO userReqDTO) {
        UserEntity user = objectMapper.convertValue(userReqDTO, UserEntity.class);
        user.setAvatar(new Photo(userReqDTO.getImage()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(userRolesService.findByName(STANDARD_ROLE));

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            return false;
        }

        userRepository.save(user);

        emailService.sendEmail(user.getEmail(), "Welcome!", "Your registration is successful!");

        return true;
    }

    @Override
    @Transactional
    public UserEntity login(UserReqDTO UserReqDTO) {

        String login = UserReqDTO.getLogin();
        UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new BadCredentialsException("User hasn't been found"));

        if (Objects.nonNull(user.getBannedDate())) {
            throw new UserHasBeenBanned("User account is disabled.");
        }

        user.setDateOfLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserReqDTO userReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userReqDTO);

        save(user);

        return userMapper.fillFromInDTO(user);
    }

    @Override
    @Transactional
    public boolean banUser(Long id, boolean verdict) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(verdict) {

            if(user.getRoles().contains(userRolesService.findByName("ADMIN"))) {
                return false;
            }

            user.setBannedDate(LocalDateTime.now());
            userRepository.save(user);

        } else {
            user.setBannedDate(null);
            userRepository.save(user);
        }

        return true;
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
    public boolean updatePassword(Long id, UserCredentialsReqDTO userChangeCredentialsReqDTO) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(!userChangeCredentialsReqDTO.getNewPassword().equals(userChangeCredentialsReqDTO.getConfirmedPassword())
                && !user.getPassword().equals(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getOldPassword()))
                && Objects.isNull(userChangeCredentialsReqDTO.getNewPassword())) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);

        return true;
    }

    @Override
    @Transactional
    public void updateAvatar(Long id, UserReqDTO userReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userReqDTO);

        save(user);
    }

    @Override
    @Transactional
    public boolean updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (userRolesReqDTO.getRoles().isEmpty()) {
           return false;
        }

        Set<UserRole> roles = new HashSet<>();

        for(UserRoleDTO role : userRolesReqDTO.getRoles()) {
            UserRole userRole = userRolesRepository.findByName(role.getRole())
                    .orElseThrow(UserRoleNotFoundException::new);
            roles.add(userRole);
        }

        user.setRoles(roles);
        save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean recoverPassword(UserCredentialsReqDTO userCredentialsReqDTO) {
        UserEntity user = userRepository.findByLogin(userCredentialsReqDTO.getLogin())
                .orElseThrow(UserNotFoundException::new);

        if(!userCredentialsReqDTO.getNewPassword().equals(userCredentialsReqDTO.getConfirmedPassword())
                && userCredentialsReqDTO.getNewPassword() != null){
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(userCredentialsReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);

        return true;
    }

}
