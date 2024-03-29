package by.iba;

import by.iba.dto.req.user.*;
import by.iba.dto.resp.user.UserDTO;
import by.iba.entity.user.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll(SearchCriteriaReqDTO searchCriteriaReqDTO);

    UserDTO findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    UserDTO save(UserReqDTO userReqDTO);

    UserEntity login(UserAuthReqDTO userAuthReqDTO);

    UserDTO update(Long id, UserPersonalDataReqDTO userPersonalDataReqDTO);

    UserDTO update(Long id, UserDTO userDTO);

    UserDTO banUser(Long id, boolean verdict);

    void confirmUser(Long id);

    void updatePassword(Long id, UserCredentialsReqDTO userCredentialsReqDTO);

    void updateAvatar(Long id, MultipartFile image) throws IOException;

    void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO);

    void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO);

}
