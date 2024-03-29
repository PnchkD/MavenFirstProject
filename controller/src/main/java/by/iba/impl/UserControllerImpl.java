package by.iba.impl;

import by.iba.UserController;
import by.iba.UserService;
import by.iba.dto.req.user.UserAvatarReqDTO;
import by.iba.dto.req.user.UserCredentialsReqDTO;
import by.iba.dto.req.user.UserPersonalDataReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.user.UserDTO;
import by.iba.helper.ControllerHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserPersonalDataReqDTO userPersonalDataReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        UserDTO user = userService.update(id, userPersonalDataReqDTO);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateAvatar(@PathVariable Long id, @RequestParam("photos") MultipartFile image) throws IOException {
        userService.updateAvatar(id, image);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User avatar was changed"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updatePassword(@PathVariable Long id, @RequestBody @Valid UserCredentialsReqDTO userCredentialsReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.updatePassword(id, userCredentialsReqDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User password was changed"));
    }

}
