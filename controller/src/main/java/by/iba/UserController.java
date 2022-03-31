package by.iba;

import by.iba.dto.req.UserChangeAvatarReqDTO;
import by.iba.dto.req.UserChangeCredentialsReqDTO;
import by.iba.dto.req.UserChangePersonalDataReqDTO;
import by.iba.dto.resp.SuccessfulDTO;
import by.iba.dto.resp.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/users")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserChangePersonalDataReqDTO userChangingInDTO);

    @PutMapping("/{id}/avatar")
    ResponseEntity<SuccessfulDTO> updateAvatar(@PathVariable Long id, @RequestBody UserChangeAvatarReqDTO userChangeAvatarInDTO);

    @PutMapping("/{id}/credentials")
    ResponseEntity<SuccessfulDTO> updatePassword(@PathVariable Long id, @RequestBody UserChangeCredentialsReqDTO userChangeCredentialsInDTO);

}
