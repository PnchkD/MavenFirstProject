import dto.UserDTO;
import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{name}")
    public UserDTO getUser(@PathVariable String name) {
        UserEntity user = userService.findByName(name).orElseThrow(NullPointerException::new);

        return userMapper.userIntoDTO(user);
    }

}
