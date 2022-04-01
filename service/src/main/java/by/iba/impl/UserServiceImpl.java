package by.iba.impl;

import by.iba.UserRolesService;
import by.iba.UserService;
import by.iba.dto.req.*;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.Photo;
import by.iba.entity.user.UserEntity;
import by.iba.entity.user.UserRole;
import by.iba.exception.AbstractInternalApplicationException;
import by.iba.exception.UserHasBeenAlreadyRegisteredException;
import by.iba.exception.UserHasBeenBanned;
import by.iba.exception.UserNotFoundException;
import by.iba.mapper.UserMapper;
import by.iba.repository.UserRepository;
import by.iba.service.impl.DefaultEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final DefaultEmailService emailService;
    private final ObjectMapper objectMapper;
    private final UserRolesService userRolesService;

    private static final String STANDARD_ROLE ="USER";

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userIntoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findByLogin(String login) {
        UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(UserNotFoundException::new);

        return modelMapper.map(user, UserDTO.class);
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

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO registerUser(UserRegistrationReqDTO userRegistrationReqDTO) {
        UserEntity user = objectMapper.convertValue(userRegistrationReqDTO, UserEntity.class);
        user.setAvatar(new Photo(userRegistrationReqDTO.getImage()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(userRolesService.findByName(STANDARD_ROLE));

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserHasBeenAlreadyRegisteredException();
        }

        userRepository.save(user);

        emailService.sendEmail(user.getEmail(), "Welcome!", "Your registration is successful!");

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public UserEntity login(UserAuthReqDTO userAuthReqDTO) {

        String login = userAuthReqDTO.getLogin();
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
    public UserDTO update(Long id, UserChangePersonalDataReqDTO userChangePersonalDataReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangePersonalDataReqDTO);

        save(user);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @Override
    @Transactional
    public void banUser(Long id, boolean verdict) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(verdict) {

            if(user.getRoles().contains(userRolesService.findByName("ADMIN"))) {
                throw new AbstractInternalApplicationException("ADMIN_CANNOT_BAN_HIMSELF");
            }

            user.setBannedDate(LocalDateTime.now());
            userRepository.save(user);

        } else {
            user.setBannedDate(null);
            userRepository.save(user);
        }
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
    public void updatePassword(Long id, UserChangeCredentialsReqDTO userChangeCredentialsReqDTO) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(!userChangeCredentialsReqDTO.getNewPassword().equals(userChangeCredentialsReqDTO.getConfirmedPassword())
                && !user.getPassword().equals(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getOldPassword()))) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateAvatar(Long id, UserChangeAvatarReqDTO userChangeAvatarReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangeAvatarReqDTO);

        save(user);
    }

    @Override
    @Transactional
    public void updateUserRole(Long id, UserChangeRoleReqDTO userChangeRoleReqDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangeRoleReqDTO);

        save(user);
    }

    @Override
    @Transactional
    public void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO) {
        UserEntity user = userRepository.findByLogin(userPasswordRecoveryReqDTO.getLogin())
                .orElseThrow(UserNotFoundException::new);

        if(!userPasswordRecoveryReqDTO.getNewPassword().equals(userPasswordRecoveryReqDTO.getConfirmedPassword())
                && userPasswordRecoveryReqDTO.getNewPassword() != null){
            throw new AbstractInternalApplicationException("PASSWORD_AND_CONFIRMATION_PASSWORD_DIFFERENT_OR_NEW_PASSWORD_IS_INVALID");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userPasswordRecoveryReqDTO.getNewPassword()));
        user.setDateOfLastUpdate(LocalDateTime.now());

        userRepository.save(user);
    }

}
