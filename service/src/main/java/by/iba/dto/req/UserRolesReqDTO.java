package by.iba.dto.req;

import by.iba.dto.resp.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesReqDTO {

    private List<UserRoleDTO> roles;

}
