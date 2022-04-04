package by.iba.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    @NotBlank(message = "validation.by.iba.role_null")
    private String role;

}
