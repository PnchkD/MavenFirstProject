import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Optional<UserEntity> findByName(String name) {
        return repository.findByName(name);
    }

}
