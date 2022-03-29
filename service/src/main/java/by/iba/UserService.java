package by.iba;

import by.iba.dto.out.UserDTO;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();
    //Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByLogin(String login);
    void save(UserEntity user);
    void save(UserEntity user, Role role);


}
