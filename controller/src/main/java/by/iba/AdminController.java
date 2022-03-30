package by.iba;

import by.iba.dto.in.UserChangeRoleInDTO;
import by.iba.dto.out.*;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;
import by.iba.exception.AbstractInternalApplicationException;
import by.iba.exception.UserNotFoundException;
import by.iba.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public UsersForAdminOutDTO getUsers() {
        List<UserForAdminOutDTO> users = userService.findAll();
        return new UsersForAdminOutDTO(users);
    }

    @PutMapping("/users/ban/{id}")
    public SuccessfulOutDTO banUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id).orElseThrow(UserNotFoundException::new);
        if(user.getRole() == Role.ADMIN) {
            throw new AbstractInternalApplicationException("ADMIN_CANNOT_BAN_HIMSELF");
        }
        userService.banUser(user);
        return new SuccessfulOutDTO("User has been banned!");
    }

    @PutMapping("/users/unban/{id}")
    public SuccessfulOutDTO unbanUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id).orElseThrow(UserNotFoundException::new);
        userService.unbanUser(user);
        return new SuccessfulOutDTO("User has been unbanned!");
    }


    @PutMapping("/users/confirm/{id}")
    public SuccessfulOutDTO confirmUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id).orElseThrow(UserNotFoundException::new);
        userService.confirmUser(user);
        return new SuccessfulOutDTO("User has been confirmed!");
    }

    @PutMapping("/users/role/{id}")
    public SuccessfulOutDTO changeUserRole(@RequestBody UserChangeRoleInDTO userChangeRoleInDTO, @PathVariable Long id) {
        UserEntity user = userService.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userMapper.fillFromInDTO(user, userChangeRoleInDTO);

        userService.save(user);

        return new SuccessfulOutDTO("User role was changed");
    }
}
