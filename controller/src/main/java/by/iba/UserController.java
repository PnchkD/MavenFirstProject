package by.iba;

import by.iba.dto.UserDTO;
import by.iba.dto.UsersDTO;
import by.iba.entity.UserEntity;
import by.iba.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import by.iba.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping()
    public UsersDTO getUsers() {
        List<UserDTO> users = userService.findAll();
        return new UsersDTO(users);
    }

/*    @GetMapping("/{name}")
    public UserDTO getUser(@PathVariable String name) {
        UserEntity user = userService.findByName(name).orElseThrow(UserNotFoundException::new);

        return userMapper.userIntoDTO(user);
    }*/

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        UserEntity user = userService.findById(id).orElseThrow(UserNotFoundException::new);

        return userMapper.userIntoDTO(user);
    }

}
