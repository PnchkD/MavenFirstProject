package by.iba.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserChangeCredentialsReqDTO {
    private String oldPassword;
    private String newPassword;
    private String confirmedPassword;
}
