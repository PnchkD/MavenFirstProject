package by.iba.service;

import by.iba.entity.user.UserRole;
import by.iba.repository.UserRepository;
import by.iba.entity.user.UserEntity;
import by.iba.repository.UserRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> myUser = userRepository.findByLogin(userName);
        Optional<UserRole> userRole = userRolesRepository.findByUserId(myUser.get().getId());

        if (myUser.isEmpty() || userRole.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = User.builder()
                .username(myUser.get().getLogin())
                .password(myUser.get().getPassword())
                .roles(userRole.get().getName())
                .build();
        return user;
    }
}
