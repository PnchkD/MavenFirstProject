package by.iba;

import by.iba.dto.req.user.UserLoginReqDTO;

public interface RecoveryCodeService {

    void sendRecoveryCode(UserLoginReqDTO userLoginReqDTO);

    void checkRecoveryCode(String code);

}
