package by.iba.dto.resp;

import by.iba.dto.AbstractDTO;
import by.iba.entity.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Set<UserRole> roles;
    private String avatar;
    private LocalDateTime dateOfLastLogin;
    private LocalDateTime dateOfApproved;
    private LocalDateTime bannedDate;
    private LocalDateTime isMailConfirmed;
    @JsonIgnore
    private String password;

}
