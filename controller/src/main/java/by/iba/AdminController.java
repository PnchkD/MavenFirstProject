package by.iba;

import by.iba.dto.req.UserChangeRoleReqDTO;
import by.iba.dto.resp.SuccessfulDTO;
import by.iba.dto.resp.UsersDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/admin")
public interface AdminController {

    @GetMapping("/users")
    ResponseEntity<UsersDTO> getUsers();

    @PutMapping("/users/ban/{id}")
    ResponseEntity<SuccessfulDTO> banUser(@PathVariable Long id);

    @PutMapping("/users/unban/{id}")
    ResponseEntity<SuccessfulDTO> unbanUser(@PathVariable Long id);

    @PutMapping("/users/confirm/{id}")
    ResponseEntity<SuccessfulDTO> confirmUser(@PathVariable Long id);

    @PutMapping("/users/role/{id}")
    ResponseEntity<SuccessfulDTO> updateUserRole(@PathVariable Long id, @RequestBody UserChangeRoleReqDTO userChangeRoleInDTO);

}
