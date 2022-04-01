package by.iba.repository;

import by.iba.entity.user.RecoveryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecoveryCodeRepository extends JpaRepository<RecoveryCode, Long> {

    Optional<RecoveryCode> findByRecoveryCode(String code);

}