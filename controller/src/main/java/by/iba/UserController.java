package by.iba;

import by.iba.dto.req.UserReqDTO;
import by.iba.dto.req.UserCredentialsReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/users")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO);

    @PatchMapping("/{id}/avatar")
    ResponseEntity<RespStatusDTO> updateAvatar(@PathVariable Long id, @RequestBody UserReqDTO userReqDTO);

    @PatchMapping("/{id}/credentials")
    ResponseEntity<RespStatusDTO> updatePassword(@PathVariable Long id, @RequestBody UserCredentialsReqDTO userCredentialsReqDTO);

}
