package by.iba;

import by.iba.dto.req.UserLoginReqDTO;

public interface RecoveryCodeService {

    void sendRecoveryCode(UserLoginReqDTO userLoginReqDTO);

    void checkRecoveryCode(String code);

}
