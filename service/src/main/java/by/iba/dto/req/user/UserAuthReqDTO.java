package by.iba.dto.req.user;

import by.iba.dto.BaseAbstractReq;
import by.iba.validation.ReqValidation;
import by.iba.validation.ValidEmail;
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
public class UserAuthReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    private String email;

}
