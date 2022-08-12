package by.iba.service;

import by.iba.exception.ResourceNotFoundException;
import by.iba.repository.UserRepository;
import by.iba.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws ResourceNotFoundException {
        UserEntity user = userRepository.findByLogin(userName)
                .orElseThrow(() -> new ResourceNotFoundException("USER_HAS_BEEN_NOT_FOUND"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        UserDetails userDetails = User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(authorities)
                //.roles(authorities.toString())
                .build();
        return userDetails;
    }
}
