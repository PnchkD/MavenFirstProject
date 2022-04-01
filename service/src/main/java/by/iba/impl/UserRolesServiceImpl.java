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
    public UserRole findByName(String name) {
        return userRolesRepository.findByName(name)
                .orElseThrow(UserRoleNotFoundException::new);
    }

}
