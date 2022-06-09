package by.iba.entity.photo;

import javax.persistence.PrePersist;
import java.util.Objects;
import java.util.UUID;

public final class UserPhotoEntityListener {

    @PrePersist
    private void prePersist(UserPhoto userPhoto) {
        if (Objects.isNull(userPhoto.getUniqueId())) {
            userPhoto.setUniqueId(UUID.randomUUID().toString());

        }
    }

}

