package by.iba.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RecoveryCodeServiceUtil {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int CODE_LENGTH = 6;
    public static final int MAX_LENGTH = 62;

    public StringBuilder generateRecoveryCode() {

        Random random = new Random();
        StringBuilder recoveryCode = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int number = random.nextInt(MAX_LENGTH);
            recoveryCode.append(ALPHABET.charAt(number));
        }

        return recoveryCode;
    }

}
