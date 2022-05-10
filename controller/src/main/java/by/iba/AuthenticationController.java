package by.iba;

import by.iba.dto.req.user.UserAuthReqDTO;
import by.iba.dto.req.user.UserLoginReqDTO;
import by.iba.dto.req.user.UserPasswordRecoveryReqDTO;
import by.iba.dto.req.user.UserReqDTO;
import by.iba.dto.resp.AccessTokenDTO;
import by.iba.dto.resp.RespStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public interface AuthenticationController {

    @PostMapping(value = "/registration")
    ResponseEntity<RespStatusDTO> registerUser(@RequestBody @Valid UserReqDTO userRegistrationInDTO, BindingResult bindingResult);

    @PostMapping()
    ResponseEntity<AccessTokenDTO> login(@RequestBody @Valid UserAuthReqDTO userAuthReqDTO, BindingResult bindingResult);

    @GetMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> passwordRecoveryWithEmail(@Valid UserLoginReqDTO userLoginReqDTO);

    @GetMapping("/password_recovery/code/{code}")
    ResponseEntity<RespStatusDTO> checkRecoveryCode(@PathVariable String code);

    @PutMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> updateUserPassword(@RequestBody @Valid UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO, BindingResult bindingResult);

}
