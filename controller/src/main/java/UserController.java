import dto.UserDTO;
import dto.UsersDTO;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping()
    public UsersDTO getUsers() {
        List<UserDTO> users = userService.findAll()
                .stream()
                .map(userMapper::userIntoDTO)
                .collect(Collectors.toList());

        return new UsersDTO(users);
    }

    @GetMapping("/{name}")
    public UserDTO getUser(@PathVariable String name) {
        UserEntity user = userService.findByName(name).orElseThrow(NullPointerException::new);

        return userMapper.userIntoDTO(user);
    }

}
