package by.iba.impl;

import by.iba.AdminController;
import by.iba.UserService;
import by.iba.dto.req.UserRolesReqDTO;
import by.iba.dto.resp.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    @Override
    public ResponseEntity<UsersDTO> getUsers() {
        List<UserDTO> users = userService.findAll();

        return ResponseEntity
                .ok()
                .body(new UsersDTO(users));
    }

    @Override
    public ResponseEntity<RespStatusDTO> banUser(@PathVariable Long id) {

        if(!userService.banUser(id, true)) {
            return ResponseEntity
                    .status(400)
                    .body(new RespStatusDTO("ADMIN_CANNOT_BAN_HIMSELF"));
        }

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER_NAS_BEEN_BANNED"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> unbanUser(@PathVariable Long id) {
        userService.banUser(id, false);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER_NAS_BEEN_BANNED"));
    }


    @Override
    public ResponseEntity<RespStatusDTO> confirmUser(@PathVariable Long id) {
        userService.confirmUser(id);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User has been confirmed"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateUserRole(@PathVariable Long id, @RequestBody UserRolesReqDTO userChangeRoleInDTO) {

        if(!userService.updateUserRole(id, userChangeRoleInDTO)) {
            ResponseEntity
                    .status(400)
                    .body(new RespStatusDTO("USER_MUST_HAVE_A_ROLE"));
        }

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER_ROLES_WAS_CHANGED"));
    }

}
