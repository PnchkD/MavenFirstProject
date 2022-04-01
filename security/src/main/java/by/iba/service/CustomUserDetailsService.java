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
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> myUser = userRepository.findByLogin(userName);

        if (myUser.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = User.builder()
                .username(myUser.get().getLogin())
                .password(myUser.get().getPassword())
                .roles(myUser.get().getRoles().toArray(new String[myUser.get().getRoles().size()]))
                .build();
        return user;
    }
}
