package by.iba.repository;

import by.iba.entity.photo.UserPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPhotoRepository extends BaseAbstractLongKeyRepository<UserPhoto> {

    Optional<UserPhoto> findByName(String name);

    Page<UserPhoto> findAllByUserId(Long userId, Pageable pageable, Specification specification);
}

