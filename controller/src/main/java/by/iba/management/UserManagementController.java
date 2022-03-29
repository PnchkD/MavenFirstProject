package by.iba.management;

import by.iba.UserService;
import by.iba.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-management")
public class UserManagementController {

    private final UserService userService;
    private final UserMapper userMapper;



}
