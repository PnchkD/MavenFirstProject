package by.iba;

import by.iba.dto.req.UserCredentialsReqDTO;

public interface RecoveryCodeService {

    void sendRecoveryCode(UserCredentialsReqDTO userCredentialsReqDTO);

    void checkRecoveryCode(String code);

}
