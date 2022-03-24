import entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAll();
    Optional<UserEntity> findByName(String name);

}
