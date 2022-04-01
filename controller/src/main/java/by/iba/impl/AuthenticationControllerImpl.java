package by.iba.impl;

import by.iba.AuthenticationController;
import by.iba.RecoveryCodeService;
import by.iba.UserService;
import by.iba.dto.req.UserPasswordRecoveryReqDTO;
import by.iba.dto.resp.AccessTokenDTO;
import by.iba.dto.resp.SuccessfulDTO;
import by.iba.dto.req.UserAuthReqDTO;
import by.iba.dto.req.UserRegistrationReqDTO;
import by.iba.exception.AbstractInternalApplicationException;
import by.iba.filter.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RecoveryCodeService recoveryCodeService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<SuccessfulDTO> registerUser(@RequestBody UserRegistrationReqDTO userRegistrationInDTO) {

        userService.registerUser(userRegistrationInDTO);

        return ResponseEntity
                .status(201)
                .body(new SuccessfulDTO("Registration is successful"));

    }

    @Override
    public ResponseEntity<AccessTokenDTO> login(@RequestBody UserAuthReqDTO userAuthReqDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userAuthInDTO.getLogin(),
                userAuthInDTO.getPassword()
        );

        authenticationManager.authenticate(authenticationToken);

        return ResponseEntity
                .ok()
                .body(objectMapper.convertValue(
                        jwtUtil.generateToken(userService.login(userAuthReqDTO)
                        ), AccessTokenDTO.class));
    }


    @Override
    public ResponseEntity<SuccessfulDTO> passwordRecoveryWithEmail(@RequestBody UserPasswordRecoveryReqDTO userPasswordRecoveryInDTO){
        recoveryCodeService.sendRecoveryCode(userPasswordRecoveryInDTO);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("Check your email"));
    }

    @Override
    public ResponseEntity<SuccessfulDTO> checkRecoveryCode(@PathVariable String code) {
        if(!recoveryCodeService.checkRecoveryCode(code)){
            throw new AbstractInternalApplicationException("RECOVERY_CODE_IS_INVALID");
        }

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("Enter your new password"));
    }

    @Override
    public ResponseEntity<SuccessfulDTO> updateUserPassword(@RequestBody UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO) {
        userService.recoverPassword(userPasswordRecoveryReqDTO);

        return ResponseEntity
                .ok()
                .body(new SuccessfulDTO("Your password is changed!"));
    }

}
