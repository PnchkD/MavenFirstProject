package by.iba;

import by.iba.dto.req.*;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO findByLogin(String login);

    UserDTO save(UserEntity user);

    UserDTO registerUser(UserRegistrationReqDTO userRegistrationReqDTO);

    UserEntity login(UserAuthReqDTO userAuthReqDTO);

    UserDTO update(Long id, UserChangePersonalDataReqDTO userChangePersonalDataReqDTO);

    void banUser(Long id, boolean verdict);

    void confirmUser(Long id);

    void updatePassword(Long id, UserChangeCredentialsReqDTO userChangeCredentialsReqDTO);

    void updateAvatar(Long id, UserChangeAvatarReqDTO userChangeAvatarReqDTO);

    void updateUserRole(Long id, UserChangeRoleReqDTO userChangeRoleReqDTO);

    void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO);

}
