package by.iba;

import by.iba.dto.req.UserReqDTO;
import by.iba.dto.req.UserCredentialsReqDTO;
import by.iba.dto.resp.AccessTokenDTO;
import by.iba.dto.resp.RespStatusDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
public interface AuthenticationController {

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RespStatusDTO> registerUser(@RequestBody UserReqDTO userRegistrationInDTO);

    @PostMapping()
    ResponseEntity<AccessTokenDTO> login(@RequestBody UserReqDTO userAuthInDTO);

    @GetMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> passwordRecoveryWithEmail(@RequestBody UserCredentialsReqDTO userCredentialsReqDTO);

    @GetMapping("/password_recovery/{code}")
    ResponseEntity<RespStatusDTO> checkRecoveryCode(@PathVariable String code);

    @PutMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> updateUserPassword(@RequestBody UserCredentialsReqDTO userCredentialsReqDTO);

}
