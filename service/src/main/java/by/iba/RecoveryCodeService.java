package by.iba;

import by.iba.dto.req.UserPasswordRecoveryReqDTO;
import by.iba.dto.resp.RecoveryCodeDTO;
import by.iba.entity.user.RecoveryCode;

public interface RecoveryCodeService {

    RecoveryCodeDTO save(RecoveryCode recoveryCode);

    void sendRecoveryCode(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO);

    boolean checkRecoveryCode(String code);

}
