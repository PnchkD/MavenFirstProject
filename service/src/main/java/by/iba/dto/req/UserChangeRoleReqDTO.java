package by.iba.dto.req;

import by.iba.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChangeRoleReqDTO {
    private Set<UserRole> roles;
}
