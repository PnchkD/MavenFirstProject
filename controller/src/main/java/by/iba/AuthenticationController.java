package by.iba;

import by.iba.dto.req.UserReqDTO;
import by.iba.dto.req.UserCredentialsReqDTO;
import by.iba.dto.resp.AccessTokenDTO;
import by.iba.dto.resp.RespStatusDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/auth")
public interface AuthenticationController {

    @PostMapping(value = "/registration")
    ResponseEntity<RespStatusDTO> registerUser(@Valid @RequestBody UserReqDTO userRegistrationInDTO, final BindingResult bindingResult);

    @PostMapping()
    ResponseEntity<AccessTokenDTO> login(@Valid @RequestBody UserReqDTO userAuthInDTO, final BindingResult bindingResult);

    @GetMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> passwordRecoveryWithEmail(@Valid @RequestBody UserCredentialsReqDTO userCredentialsReqDTO);

    @GetMapping("/password_recovery/{code}")
    ResponseEntity<RespStatusDTO> checkRecoveryCode(@PathVariable String code);

    @PutMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> updateUserPassword(@Valid @RequestBody UserCredentialsReqDTO userCredentialsReqDTO, final BindingResult bindingResult);

}
