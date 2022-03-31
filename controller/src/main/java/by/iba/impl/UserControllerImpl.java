package by.iba.impl;

import by.iba.UserController;
import by.iba.UserService;
import by.iba.dto.req.UserChangeAvatarReqDTO;
import by.iba.dto.req.UserChangeCredentialsReqDTO;
import by.iba.dto.req.UserChangePersonalDataReqDTO;
import by.iba.dto.resp.SuccessfulDTO;
import by.iba.dto.resp.UserDTO;
import by.iba.entity.user.UserEntity;
import by.iba.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import by.iba.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {

        UserDTO user = userService.findById(id);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @Override
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserChangePersonalDataReqDTO userChangingInDTO) {

        UserDTO user = userService.update(id, userChangingInDTO);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @Override
    public ResponseEntity<SuccessfulDTO> updateAvatar(@PathVariable Long id, @RequestBody UserChangeAvatarReqDTO userChangeAvatarInDTO) {

        userService.updateAvatar(id, userChangeAvatarInDTO);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("User avatar was changed"));
    }

    @Override
    public ResponseEntity<SuccessfulDTO> updatePassword(@PathVariable Long id, @RequestBody UserChangeCredentialsReqDTO userChangeCredentialsInDTO) {

        userService.updatePassword(id, userChangeCredentialsInDTO);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("User password was changed"));
    }

}
