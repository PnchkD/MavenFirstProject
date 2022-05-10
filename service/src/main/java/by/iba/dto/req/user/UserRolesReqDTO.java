package by.iba.dto.req.user;

import by.iba.dto.BaseAbstractReq;
import by.iba.dto.resp.user.UserRoleDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public class UserRolesReqDTO extends BaseAbstractReq {

    @Valid
    private List<UserRoleDTO> roles;

}
