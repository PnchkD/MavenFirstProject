package by.iba.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RecoveryCodeUtil {

    public StringBuilder generateRecoveryCode() {

        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int codeLength = 6;
        int maxLength = 62;

        Random random = new Random();
        StringBuilder recoveryCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            int number = random.nextInt(maxLength);
            recoveryCode.append(alphabet.charAt(number));
        }

        return recoveryCode;
    }

}
