package by.iba.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String image;

}
