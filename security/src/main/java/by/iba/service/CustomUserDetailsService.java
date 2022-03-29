package by.iba.service;

import by.iba.repository.UserRepository;
import by.iba.entity.user.UserEntity;
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
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> myUser = userRepository.findByLogin(userName);
        if (myUser.isEmpty()) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }
        UserDetails user = User.builder()
                .username(myUser.get().getLogin())
                .password(myUser.get().getPassword())
                .roles(myUser.get().getRole().toString())
                .build();
        return user;
    }
}
