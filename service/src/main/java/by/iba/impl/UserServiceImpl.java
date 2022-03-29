package by.iba.impl;

import by.iba.UserService;
import by.iba.dto.out.UserDTO;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;
import by.iba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import by.iba.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    private final Role standardRole = Role.USER;

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::userIntoDTO)
                .collect(Collectors.toList());
    }

/*    @Override
    public Optional<UserEntity> findByName(String name) {
        return repository.findByName(name);
    }*/

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
            user.setMailConfirmed(false);
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
            user.setMailConfirmed(false);
        }

        repository.save(user);
    }
}
