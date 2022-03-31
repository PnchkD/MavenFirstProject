package by.iba;

import by.iba.entity.user.UserRole;

public interface UserRolesService {

    UserRole findByName(String login);

    UserRole findByUserId(Long id);

    UserRole createRole(String role, Long id);

}
