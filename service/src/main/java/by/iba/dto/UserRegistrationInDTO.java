package by.iba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationInDTO {
    private String name;
    private String login;
    private String password;
    private String email;
}
