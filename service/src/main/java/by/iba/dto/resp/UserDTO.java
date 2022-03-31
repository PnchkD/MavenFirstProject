package by.iba.dto.resp;

import by.iba.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String avatar;
    private LocalDateTime dateOfLastLogin;
    private LocalDateTime dateOfApproved;
    private LocalDateTime bannedDate;
    private LocalDateTime isMailConfirmed;
    @JsonIgnore
    private String password;

}
