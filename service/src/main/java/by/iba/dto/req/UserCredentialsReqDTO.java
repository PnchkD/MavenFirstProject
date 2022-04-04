package by.iba.dto.req;

import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsReqDTO {

    @NotBlank(message = "validation.by.iba.auth.user_req.login_null")
    private String login;

    @NotBlank(message = "validation.by.iba.account.user.old_password_null")
    private String oldPassword;

    @Size(message = "validation.by.iba.password_rule",
            min = ReqValidation.MIN_PASSWORD_LENGTH,
            max = ReqValidation.MAX_PASSWORD_LENGTH)
    @NotBlank(message = "validation.by.iba.account.user.new_password_null")
    private String newPassword;

    @NotBlank(message = "validation.by.iba.account.user.confirmed_password_null")
    private String confirmedPassword;

}
