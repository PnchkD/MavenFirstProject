package by.iba.dto.req.user;

import by.iba.dto.BaseAbstractReq;
import by.iba.validation.ReqValidation;
import by.iba.validation.ValidEmail;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDTO extends BaseAbstractReq {

    @NotBlank(message = "First name cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_FIRST_NAME_LENGTH)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_LAST_NAME_LENGTH)
    private String lastName;

    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    @Size(message = "Password is too large or too small",
            min = ReqValidation.MIN_PASSWORD_LENGTH,
            max = ReqValidation.MAX_PASSWORD_LENGTH)
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Size(message = "Email is too large",
            max = ReqValidation.MAX_EMAIL_LENGTH)
    @ValidEmail(message = "Email is not Valid")
    private String email;

    private String isAutoPicker;

}
