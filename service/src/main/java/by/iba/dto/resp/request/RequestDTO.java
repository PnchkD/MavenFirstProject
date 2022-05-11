package by.iba.dto.resp.request;

import by.iba.dto.AbstractDTO;
import by.iba.dto.resp.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO extends AbstractDTO {

    private String wishes;
    private String country;
    private String city;
    private UserDTO fromUser;

}
