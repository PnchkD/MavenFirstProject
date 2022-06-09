package by.iba.entity.photo;

import by.iba.entity.AbstractEntity;
import by.iba.entity.DocumentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "user_photos")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(UserPhotoEntityListener.class)
public class UserPhoto extends AbstractEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "unique_id", unique = true)
    private String uniqueId;

    @Column(name = "file_name", nullable = false)
    private String name;

    @Column(name = "file_path")
    private String path;

    @Column(name = "file_size")
    private Long size;

    @Column(name = "external_key", unique = true)
    private String externalKey;

    @Column(name = "status", nullable = false)
    private DocumentStatus status;

    @Column(name = "deletion_date")
    private LocalDate deletionDate;

}

