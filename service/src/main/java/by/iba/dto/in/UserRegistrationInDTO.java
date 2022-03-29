package by.iba.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationInDTO {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String image;
}
