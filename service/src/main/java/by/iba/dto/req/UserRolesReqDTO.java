package by.iba.dto.req;

import by.iba.dto.BaseAbstractReq;
import by.iba.dto.resp.UserRoleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.Valid;
import java.io.Serializable;
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
