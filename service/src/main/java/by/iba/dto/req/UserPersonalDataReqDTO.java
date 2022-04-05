package by.iba.dto.req;

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
public class UserPersonalDataReqDTO extends BaseAbstractReq {

    @NotBlank(message = "First name cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_FIRST_NAME_LENGTH)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_LAST_NAME_LENGTH)
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Size(message = "Email is too large",
            max = ReqValidation.MAX_EMAIL_LENGTH)
    @ValidEmail(message = "Email is not Valid")
    private String email;

    private String image;
}
