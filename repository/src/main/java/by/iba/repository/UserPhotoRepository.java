package by.iba.repository;

import by.iba.entity.photo.UserPhoto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPhotoRepository extends BaseAbstractLongKeyRepository<UserPhoto> {

    Optional<UserPhoto> findByName(String name);

    List<UserPhoto> findAllByUserId(Long userId);

}

