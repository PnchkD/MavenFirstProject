package by.iba;

import by.iba.dto.req.*;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO save(UserReqDTO userReqDTO);

    UserEntity login(UserReqDTO userReqDTO);

    UserDTO update(Long id, UserReqDTO userReqDTO);

    UserDTO banUser(Long id, boolean verdict);

    void confirmUser(Long id);

    void updatePassword(Long id, UserCredentialsReqDTO userCredentialsReqDTO);

    void updateAvatar(Long id, UserReqDTO userReqDTO);

    void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO);

    void recoverPassword(UserCredentialsReqDTO userCredentialsReqDTO);

}
