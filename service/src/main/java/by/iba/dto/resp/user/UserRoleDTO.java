package by.iba.dto.resp.user;

import by.iba.dto.BaseAbstractReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO extends BaseAbstractReq {

    @NotBlank(message = "Role cannot be empty")
    private String role;

}
