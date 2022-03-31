package by.iba.impl;

import by.iba.RecoveryCodeService;
import by.iba.dto.req.UserPasswordRecoveryReqDTO;
import by.iba.dto.resp.RecoveryCodeDTO;
import by.iba.entity.user.RecoveryCode;
import by.iba.entity.user.UserEntity;
import by.iba.exception.RecoveryCodeNotFound;
import by.iba.exception.UserNotFoundException;
import by.iba.repository.RecoveryCodeRepository;
import by.iba.repository.UserRepository;
import by.iba.service.impl.DefaultEmailService;
import by.iba.util.RecoveryCodeUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RecoveryCodeImpl implements RecoveryCodeService {

    private final RecoveryCodeUtil recoveryCodeUtil;
    private final RecoveryCodeRepository recoveryCodeRepository;
    private final UserRepository userRepository;
    private final DefaultEmailService emailService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RecoveryCodeDTO save(RecoveryCode recoveryCode) {

        recoveryCodeRepository.save(recoveryCode);

        return modelMapper.map(recoveryCode, RecoveryCodeDTO.class);
    }

    @Override
    @Transactional
    public void sendRecoveryCode(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO) {
        UserEntity user = userRepository.findByLogin(userPasswordRecoveryReqDTO.getLogin())
                .orElseThrow(UserNotFoundException::new);

        RecoveryCode newRecoveryCode = new RecoveryCode();
        newRecoveryCode.setRecoveryCode(recoveryCodeUtil.generateRecoveryCode().toString());

        recoveryCodeRepository.save(newRecoveryCode);

        emailService.sendEmail(user.getEmail(), "Password recovery", newRecoveryCode.getRecoveryCode());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkRecoveryCode(String code) {
        RecoveryCode recoveryCode = recoveryCodeRepository.findByRecoveryCode(code)
                .orElseThrow(RecoveryCodeNotFound::new);

        return code.equals(recoveryCode.getRecoveryCode());
    }

}
