package by.iba.dto.req;

import by.iba.dto.BaseAbstractReq;
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
public class UserPasswordRecoveryReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Login cannot be empty")
    private String login;

    @Size(message = "Length is too large or too small",
            min = ReqValidation.MIN_PASSWORD_LENGTH,
            max = ReqValidation.MAX_PASSWORD_LENGTH)
    @NotBlank(message = "New password cannot be empty")
    private String newPassword;

    @NotBlank(message = "Confirmed password cannot be empty")
    private String confirmedPassword;

}
