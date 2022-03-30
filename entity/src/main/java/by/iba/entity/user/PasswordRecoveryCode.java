package by.iba.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PasswordRecoveryCode {
    private String login;
    private String recoveryCode;
}
