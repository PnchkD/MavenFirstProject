package by.iba.impl;

import by.iba.AdminController;
import by.iba.UserService;
import by.iba.dto.req.UserChangeRoleReqDTO;
import by.iba.dto.resp.*;
import by.iba.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UsersDTO> getUsers() {
        List<UserDTO> users = userService.findAll();

        return ResponseEntity
                .ok()
                .body(new UsersDTO(users));
    }

    @Override
    public ResponseEntity<SuccessfulDTO> banUser(@PathVariable Long id) {
        userService.banUser(id, true);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("User has been banned"));
    }

    @Override
    public ResponseEntity<SuccessfulDTO> unbanUser(@PathVariable Long id) {
        userService.banUser(id, false);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("User has been unbanned"));
    }


    @Override
    public ResponseEntity<SuccessfulDTO> confirmUser(@PathVariable Long id) {
        userService.confirmUser(id);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("User has been confirmed"));
    }

    @Override
    public ResponseEntity<SuccessfulDTO> updateUserRole(@PathVariable Long id, @RequestBody UserChangeRoleReqDTO userChangeRoleInDTO) {
        userService.updateUserRole(id, userChangeRoleInDTO);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("User role was changed"));
    }

}
