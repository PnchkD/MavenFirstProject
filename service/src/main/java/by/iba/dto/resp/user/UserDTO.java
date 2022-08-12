package by.iba.dto.resp.user;

import by.iba.dto.AbstractDTO;
import by.iba.dto.resp.PhotoForAmazonResp;
import by.iba.entity.SupportedAuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private List<UserRoleDTO> roles;
    private PhotoForAmazonResp avatar;
    private LocalDateTime dateOfLastLogin;
    private LocalDateTime dateOfApproved;
    private LocalDateTime bannedDate;
    private LocalDateTime isMailConfirmed;
    @JsonIgnore
    private String password;
    private SupportedAuthProvider provider;

}
