package by.iba.impl;

import by.iba.UserController;
import by.iba.UserService;
import by.iba.dto.req.UserReqDTO;
import by.iba.dto.req.UserCredentialsReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.UserDTO;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO) {

        UserDTO user = userService.update(id, userReqDTO);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateAvatar(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO) {

        userService.updateAvatar(id, userReqDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User avatar was changed"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updatePassword(@PathVariable Long id, @RequestBody UserCredentialsReqDTO userCredentialsReqDTO) {

        userService.updatePassword(id, userCredentialsReqDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User password was changed"));
    }

}
