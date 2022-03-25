import dto.UserDTO;
import entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findById(Long id);

}
