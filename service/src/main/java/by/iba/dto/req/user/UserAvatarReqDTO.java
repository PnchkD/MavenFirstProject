package by.iba.dto.req.user;

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
public class UserAvatarReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Image cannot be empty")
    private String image;

}
