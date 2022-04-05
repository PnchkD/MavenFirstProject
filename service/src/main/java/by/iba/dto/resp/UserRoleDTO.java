package by.iba.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    @NotBlank(message = "Role cannot be empty")
    private String role;

}
