package by.iba.impl;

import by.iba.UserService;
import by.iba.dto.out.UserForAdminOutDTO;
import by.iba.entity.user.PasswordRecoveryCode;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;
import by.iba.repository.UserRepository;
import lombok.AllArgsConstructor;
import by.iba.mapper.UserMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.EmailService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final EmailService mailService;
    private final Role standardRole = Role.USER;

    private final PasswordRecoveryCode passwordRecoveryCode;

    @Override
    @Transactional
    public List<UserForAdminOutDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::userForAdminOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {
        if(user.getId() == null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(standardRole);
            user.setDateOfCreation(LocalDateTime.now());
        }

        user.setDateOfLastUpdate(LocalDateTime.now());
        repository.save(user);
    }

    @Override
    @Transactional
    public void save(UserEntity user, Role role) {
        if(user.getId() == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(role);
            user.setDateOfCreation(LocalDateTime.now());
            user.setDateOfLastUpdate(LocalDateTime.now());
        }

        repository.save(user);
    }

    @Override
    @Transactional
    public void updateLastDateOfLogin(UserEntity user) {
        user.setDateOfLastLogin(LocalDateTime.now());
        repository.save(user);
    }

    @Override
    @Transactional
    public void banUser(UserEntity user) {
        user.setBannedDate(LocalDateTime.now());
        repository.save(user);
    }

    @Override
    @Transactional
    public void unbanUser(UserEntity user) {
        user.setBannedDate(null);
        repository.save(user);
    }

    @Override
    @Transactional
    public void confirmUser(UserEntity user) {
        user.setDateOfConfirm(LocalDateTime.now());
        repository.save(user);
    }

    @Override
    public String generatePasswordRecovery(UserEntity user) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int codeLength = 6;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i < codeLength;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }

        passwordRecoveryCode.setLogin(user.getLogin());
        passwordRecoveryCode.setRecoveryCode(sb.toString());

        return sb.toString();
    }

    @Override
    public boolean checkRecoveryCode(String code) {
        if(code.equals(passwordRecoveryCode.getRecoveryCode())){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void setNewUserPassword(String password) {
        UserEntity user = findByLogin(passwordRecoveryCode.getLogin())
                .orElseThrow(() -> new BadCredentialsException("User hasn't been found"));

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setDateOfLastUpdate(LocalDateTime.now());
        repository.save(user);
    }


}
