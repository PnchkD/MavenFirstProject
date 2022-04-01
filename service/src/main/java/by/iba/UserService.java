package by.iba;

import by.iba.dto.req.*;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO save(UserEntity user);

    boolean registerUser(UserReqDTO userReqDTO);

    UserEntity login(UserReqDTO userReqDTO);

    UserDTO update(Long id, UserReqDTO userReqDTO);

    boolean banUser(Long id, boolean verdict);

    void confirmUser(Long id);

    boolean updatePassword(Long id, UserCredentialsReqDTO userCredentialsReqDTO);

    void updateAvatar(Long id, UserReqDTO userReqDTO);

    boolean updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO);

    boolean recoverPassword(UserCredentialsReqDTO userCredentialsReqDTO);

}
