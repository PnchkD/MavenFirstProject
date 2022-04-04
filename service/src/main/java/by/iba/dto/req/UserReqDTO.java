package by.iba.dto.req;

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

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    @ValidEmail(message = "validation.by.iba.auth.user_req.email")
    private String email;

    private String image;

}
