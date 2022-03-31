package by.iba.impl;

import by.iba.UserRolesService;
import by.iba.entity.user.UserRole;
import by.iba.exception.UserRoleNotFoundException;
import by.iba.repository.UserRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;

    @Override
    @Transactional(readOnly = true)
    public UserRole findByName(String login) {
        return userRolesRepository.findByName("ADMIN")
                .orElseThrow(UserRoleNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole findByUserId(Long id) {
        return userRolesRepository.findByUserId(id)
                .orElseThrow(UserRoleNotFoundException::new);
    }

    @Override
    @Transactional
    public UserRole createRole(String role, Long id) {

        UserRole userRole = new UserRole();
        userRole.setName(role);
        userRole.setUserId(id);
        userRole.setDateOfCreation(LocalDateTime.now());
        userRole.setDateOfLastUpdate(LocalDateTime.now());

        userRolesRepository.save(userRole);
        return userRole;
    }

}
