package by.iba;

import by.iba.dto.in.UserChangePasswordInDTO;
import by.iba.dto.in.UserPasswordRecoveryInDTO;
import by.iba.dto.out.AccessTokenOutDTO;
import by.iba.dto.out.SuccessfulOutDTO;
import by.iba.dto.in.UserAuthInDTO;
import by.iba.dto.in.UserRegistrationInDTO;
import by.iba.entity.user.Photo;
import by.iba.entity.user.UserEntity;
import by.iba.exception.AbstractInternalApplicationException;
import by.iba.exception.UserHasBeenAlreadyRegisteredException;
import by.iba.exception.UserHasBeenBanned;
import by.iba.filter.AccessToken;
import by.iba.filter.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import service.impl.DefaultEmailService;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final DefaultEmailService emailService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulOutDTO registerUser(@RequestBody UserRegistrationInDTO userRegistrationInDTO) {

        UserEntity user = objectMapper.convertValue(userRegistrationInDTO, UserEntity.class);
        user.setAvatar(new Photo(userRegistrationInDTO.getImage()));

        if (userService.findByLogin(user.getLogin()).isPresent()) {
            throw new UserHasBeenAlreadyRegisteredException();
        }

        userService.save(user);

        emailService.sendEmail(user.getEmail(), "Welcome!", "Your registration is successful!");

        return new SuccessfulOutDTO("Registration is successful");
    }

    @PostMapping("/auth")
    public AccessTokenOutDTO auth(@RequestBody UserAuthInDTO userAuthInDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userAuthInDTO.getLogin(),
                userAuthInDTO.getPassword()
        );

        authenticationManager.authenticate(authenticationToken);

        String login = userAuthInDTO.getLogin();
        UserEntity user = userService.findByLogin(login)
                .orElseThrow(() -> new BadCredentialsException("User hasn't been found"));

        if(user.getBannedDate() != null) {
            throw new UserHasBeenBanned("User account is disabled.");
        }

        userService.updateLastDateOfLogin(user);

        AccessToken accessToken = jwtUtil.generateToken(user);

        return objectMapper.convertValue(accessToken, AccessTokenOutDTO.class);
    }


    @GetMapping("/password_recovery")
    public SuccessfulOutDTO passwordRecoveryWithEmail(@RequestBody UserPasswordRecoveryInDTO userPasswordRecoveryInDTO){
        UserEntity user = userService.findByLogin(userPasswordRecoveryInDTO.getLogin())
                .orElseThrow(() -> new BadCredentialsException("User hasn't been found"));

        String recoveryCode = userService.generatePasswordRecovery(user);

        emailService.sendEmail(user.getEmail(), "Password recovery", recoveryCode);

        return new SuccessfulOutDTO("Check your email");
    }

    @GetMapping("/password_recovery/{code}")
    public SuccessfulOutDTO passwordRecoveryWithCode(@PathVariable String code) {
        if(!userService.checkRecoveryCode(code)){
            throw new AbstractInternalApplicationException("RECOVERY_CODE_IS_INVALID");
        }

        return new SuccessfulOutDTO("Enter your new password");
    }

    @PutMapping("/password_recovery")
    public SuccessfulOutDTO changeUserPassword(@RequestBody UserChangePasswordInDTO userChangePasswordInDTO) {
        if(!userChangePasswordInDTO.getPassword().equals(userChangePasswordInDTO.getConfirmedPassword())
                && userChangePasswordInDTO.getPassword() != null){
            throw new AbstractInternalApplicationException("PASSWORD_AND_CONFIRMATION_PASSWORD_DIFFERENT!");
        }

        userService.setNewUserPassword(userChangePasswordInDTO.getPassword());

        return new SuccessfulOutDTO("Your password is changed!");
    }

}
