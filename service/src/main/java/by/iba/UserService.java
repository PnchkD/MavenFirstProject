package by.iba;

import by.iba.dto.out.UserForAdminOutDTO;
import by.iba.dto.out.UserOutDTO;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserForAdminOutDTO> findAll();
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByLogin(String login);
    void save(UserEntity user);
    void save(UserEntity user, Role role);
    void updateLastDateOfLogin(UserEntity user);
    void banUser(UserEntity user);
    void unbanUser(UserEntity user);
    void confirmUser(UserEntity user);
    String generatePasswordRecovery(UserEntity user);
    boolean checkRecoveryCode(String code);
    void setNewUserPassword(String password);

}
