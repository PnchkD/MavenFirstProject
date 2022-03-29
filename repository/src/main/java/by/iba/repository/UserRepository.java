package by.iba.repository;

import by.iba.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByLogin(String login);
}
