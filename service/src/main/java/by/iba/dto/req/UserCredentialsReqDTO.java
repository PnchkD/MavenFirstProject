package by.iba.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentialsReqDTO {

    private String login;
    private String oldPassword;
    private String newPassword;
    private String confirmedPassword;

}
