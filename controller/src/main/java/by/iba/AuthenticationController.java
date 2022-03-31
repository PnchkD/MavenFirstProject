package by.iba;

import by.iba.dto.req.UserAuthReqDTO;
import by.iba.dto.req.UserPasswordRecoveryReqDTO;
import by.iba.dto.req.UserRegistrationReqDTO;
import by.iba.dto.resp.AccessTokenDTO;
import by.iba.dto.resp.SuccessfulDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
public interface AuthenticationController {

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SuccessfulDTO> registerUser(@RequestBody UserRegistrationReqDTO userRegistrationInDTO);

    @PostMapping()
    ResponseEntity<AccessTokenDTO> login(@RequestBody UserAuthReqDTO userAuthInDTO);

    @GetMapping("/password_recovery")
    ResponseEntity<SuccessfulDTO> passwordRecoveryWithEmail(@RequestBody UserPasswordRecoveryReqDTO userPasswordRecoveryInDTO);

    @GetMapping("/password_recovery/{code}")
    ResponseEntity<SuccessfulDTO> checkRecoveryCode(@PathVariable String code);

    @PutMapping("/password_recovery")
    ResponseEntity<SuccessfulDTO> updateUserPassword(@RequestBody UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO);

}
