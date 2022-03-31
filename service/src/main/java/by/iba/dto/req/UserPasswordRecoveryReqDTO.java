package by.iba.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordRecoveryReqDTO {
    private String login;
    private String newPassword;
    private String confirmedPassword;
}
