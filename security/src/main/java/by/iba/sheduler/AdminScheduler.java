package by.iba.sheduler;

import by.iba.UserService;
import by.iba.entity.user.Role;
import by.iba.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "admin.enabled", havingValue = "true")
public class AdminScheduler {
    private final UserService userService;

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.login}")
    private String adminLogin;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;


    @Scheduled(fixedRateString = "${scheduler.admin.adminVerification.fixedRateMs}")
    public void verifyAdmin(){
        Optional<UserEntity> optionalAdmin = userService.findByLogin(adminLogin);

        if(optionalAdmin.isEmpty()){

            UserEntity user = new UserEntity();
            user.setFirstName(adminName);
            user.setLastName(adminName);
            user.setLogin(adminLogin);
            user.setPassword(adminPassword);
            user.setEmail(adminEmail);

            userService.save(user, Role.ADMIN);

            return;
        }

        UserEntity user = optionalAdmin.get();

        if(user.getRole() != Role.ADMIN){

            user.setRole(Role.ADMIN);
            userService.save(user);

        }

    }
}
