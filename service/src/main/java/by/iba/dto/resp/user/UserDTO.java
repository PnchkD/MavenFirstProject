package by.iba.dto.resp.user;

import by.iba.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO {

    private String firstName;
    private String lastName;
    private String email;
    private List<UserRoleDTO> roles;
    private String avatar;
    private LocalDateTime dateOfLastLogin;
    private LocalDateTime dateOfApproved;
    private LocalDateTime bannedDate;
    private LocalDateTime isMailConfirmed;
    @JsonIgnore
    private String password;

}
