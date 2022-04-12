package by.iba;

import by.iba.dto.req.*;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll(UserSortCriteriaReqDTO userSortCriteriaReqDTO);

    List<UserDTO> searchUser(String search);

    UserDTO findById(Long id);

    UserDTO save(UserReqDTO userReqDTO);

    UserEntity login(UserAuthReqDTO userAuthReqDTO);

    UserDTO update(Long id, UserPersonalDataReqDTO userPersonalDataReqDTO);

    UserDTO banUser(Long id, boolean verdict);

    void confirmUser(Long id);

    void updatePassword(Long id, UserCredentialsReqDTO userCredentialsReqDTO);

    void updateAvatar(Long id, UserAvatarReqDTO userAvatarReqDTO);

    void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO);

    void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO);

}
