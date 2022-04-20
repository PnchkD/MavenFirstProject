package by.iba.impl;

import by.iba.RecoveryCodeService;
import by.iba.dto.req.UserLoginReqDTO;
import by.iba.entity.user.RecoveryCode;
import by.iba.entity.user.UserEntity;
import by.iba.exception.ResourceNotFoundException;
import by.iba.repository.RecoveryCodeRepository;
import by.iba.repository.UserRepository;
import by.iba.service.impl.DefaultEmailService;
import by.iba.util.RecoveryCodeServiceUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecoveryCodeImpl implements RecoveryCodeService {

    private final RecoveryCodeServiceUtil recoveryCodeServiceUtil;
    private final RecoveryCodeRepository recoveryCodeRepository;
    private final UserRepository userRepository;
    private final DefaultEmailService emailService;

    @Override
    @Transactional
    public void sendRecoveryCode(UserLoginReqDTO userLoginReqDTO) {
        UserEntity user = userRepository.findByLogin(userLoginReqDTO.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException("INVALID_USER"));

        RecoveryCode newRecoveryCode = new RecoveryCode();
        newRecoveryCode.setRecoveryCode(recoveryCodeServiceUtil.generateRecoveryCode().toString());

        recoveryCodeRepository.save(newRecoveryCode);

        emailService.sendEmail(user.getEmail(), "Password recovery", newRecoveryCode.getRecoveryCode());
    }

    @Override
    @Transactional(readOnly = true)
    public void checkRecoveryCode(String code) {
        Optional<RecoveryCode> recoveryCode = recoveryCodeRepository.findByRecoveryCode(code);

        if(recoveryCode.isEmpty() || !code.equals(recoveryCode.get().getRecoveryCode())) {
            throw new ResourceNotFoundException("INVALID_RECOVERY_CODE");
        }

    }

}
