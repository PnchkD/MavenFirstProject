package by.iba;

import by.iba.dto.UserDTO;
import by.iba.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();
    //Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByLogin(String login);
    void save(UserEntity user);


}
