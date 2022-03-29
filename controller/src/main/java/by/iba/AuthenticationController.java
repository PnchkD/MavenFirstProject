package by.iba;

import by.iba.dto.out.AccessTokenOutDTO;
import by.iba.dto.out.ErrorDTO;
import by.iba.dto.out.SuccessfulOutDTO;
import by.iba.dto.in.UserAuthInDTO;
import by.iba.dto.in.UserRegistrationInDTO;
import by.iba.entity.user.Photo;
import by.iba.entity.user.UserEntity;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());

        return "registration";
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulOutDTO registerUser(@RequestBody UserRegistrationInDTO userRegistrationInDTO) {

        UserEntity user = objectMapper.convertValue(userRegistrationInDTO, UserEntity.class);
        user.setAvatar(new Photo(userRegistrationInDTO.getImage()));

        if (userService.findByLogin(user.getLogin()).isPresent()) {
            throw new UserHasBeenAlreadyRegisteredException();
        }

        userService.save(user);

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
            throw new UserHasBeenBanned();
        }

        AccessToken accessToken = jwtUtil.generateToken(user);

        return objectMapper.convertValue(accessToken, AccessTokenOutDTO.class);
    }


}
