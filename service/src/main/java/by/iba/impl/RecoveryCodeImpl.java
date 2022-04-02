package by.iba.impl;

import by.iba.RecoveryCodeService;
import by.iba.dto.req.UserCredentialsReqDTO;
import by.iba.entity.user.RecoveryCode;
import by.iba.entity.user.UserEntity;
import by.iba.exception.RecoveryCodeNotFoundException;
import by.iba.exception.UserNotFoundException;
import by.iba.repository.RecoveryCodeRepository;
import by.iba.repository.UserRepository;
import by.iba.service.impl.DefaultEmailService;
import by.iba.util.RecoveryCodeServiceUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RecoveryCodeImpl implements RecoveryCodeService {

    private final RecoveryCodeServiceUtil recoveryCodeServiceUtil;
    private final RecoveryCodeRepository recoveryCodeRepository;
    private final UserRepository userRepository;
    private final DefaultEmailService emailService;

    @Override
    @Transactional
    public void sendRecoveryCode(UserCredentialsReqDTO userCredentialsReqDTO) {
        UserEntity user = userRepository.findByLogin(userCredentialsReqDTO.getLogin())
                .orElseThrow(UserNotFoundException::new);

        RecoveryCode newRecoveryCode = new RecoveryCode();
        newRecoveryCode.setRecoveryCode(recoveryCodeServiceUtil.generateRecoveryCode().toString());

        recoveryCodeRepository.save(newRecoveryCode);

        emailService.sendEmail(user.getEmail(), "Password recovery", newRecoveryCode.getRecoveryCode());
    }

    @Override
    @Transactional(readOnly = true)
    public void checkRecoveryCode(String code) {
        RecoveryCode recoveryCode = recoveryCodeRepository.findByRecoveryCode(code)
                .orElseThrow(RecoveryCodeNotFoundException::new);

        if(!code.equals(recoveryCode.getRecoveryCode())) {
            throw new RecoveryCodeNotFoundException();
        }

    }

}
