package by.iba;

import by.iba.dto.req.user.UserAvatarReqDTO;
import by.iba.dto.req.user.UserPersonalDataReqDTO;
import by.iba.dto.req.user.UserCredentialsReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequestMapping(path = "/api/v1/users")
@CrossOrigin(origins = "*")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserPersonalDataReqDTO userPersonalDataReqDTO, BindingResult bindingResult);

    @PatchMapping("/{id}/avatar")
    ResponseEntity<RespStatusDTO> updateAvatar(@PathVariable Long id, @RequestParam("image") MultipartFile image) throws IOException;

    @PatchMapping("/{id}/credentials")
    ResponseEntity<RespStatusDTO> updatePassword(@PathVariable Long id, @RequestBody @Valid UserCredentialsReqDTO userCredentialsReqDTO, BindingResult bindingResult);

}
