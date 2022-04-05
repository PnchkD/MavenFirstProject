package by.iba.dto.req;

import by.iba.dto.BaseAbstractReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Login cannot be empty")
    private String login;

}
