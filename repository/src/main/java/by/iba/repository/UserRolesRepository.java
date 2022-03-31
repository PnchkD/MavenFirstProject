package by.iba.repository;

import by.iba.entity.user.UserEntity;
import by.iba.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);

    //@Query("select c from UserRole c join UserEntity b where c. = :id")
    Optional<UserRole> findByUserId(Long id);

}