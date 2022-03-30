package by.iba;

import by.iba.dto.in.UserChangeAvatarInDTO;
import by.iba.dto.in.UserChangeCredentialsInDTO;
import by.iba.dto.in.UserChangingInDTO;
import by.iba.dto.out.SuccessfulOutDTO;
import by.iba.dto.out.UserOutDTO;
import by.iba.entity.user.UserEntity;
import by.iba.exception.UserNotFoundException;
import by.iba.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import by.iba.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/me")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserOutDTO getUserById(@PathVariable Long id) {
        UserEntity user = userService.findById(id).orElseThrow(UserNotFoundException::new);

        return userMapper.userIntoDTO(user);
    }

    @PutMapping("/{id}")
    public SuccessfulOutDTO changeUser(@RequestBody UserChangingInDTO userChangingInDTO, @PathVariable Long id) {
        UserEntity user = userService.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangingInDTO);
        userService.save(user);

        return new SuccessfulOutDTO("User was changed");
    }

    @PutMapping("/avatar/{id}")
    public SuccessfulOutDTO changeAvatar(@RequestBody UserChangeAvatarInDTO userChangeAvatarInDTO, @PathVariable Long id) {
        UserEntity user = userService.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangeAvatarInDTO);

        userService.save(user);

        return new SuccessfulOutDTO("User was changed");
    }

    @PutMapping("/credentials/{id}")
    public SuccessfulOutDTO changeUserPassword(@RequestBody UserChangeCredentialsInDTO userChangeCredentialsInDTO, @PathVariable Long id) {
        UserEntity user = userService.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangeCredentialsInDTO);

        userService.save(user);

        return new SuccessfulOutDTO("User was changed");
    }

}
