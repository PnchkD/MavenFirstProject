import dto.UserDTO;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::userIntoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }
}
