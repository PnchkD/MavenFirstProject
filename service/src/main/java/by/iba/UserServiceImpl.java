package by.iba;

import by.iba.dto.UserDTO;
import by.iba.entity.UserEntity;
import by.iba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import by.iba.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    private final String standardRole = "USER";
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
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {
        if(user.getId() == null){
            user.setLogin(user.getLogin());
            user.setPassword(user.getPassword());
            user.setRole(standardRole);
        }

        repository.save(user);
    }
}
