package by.iba.entity.photo;

import by.iba.entity.AbstractEntity;
import by.iba.entity.DocumentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDate;

@Entity
@Table(name = "user_photos")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(UserPhotoEntityListener.class)
public class UserPhoto extends AbstractEntity implements MultipartFile {

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

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {

    }

    @Override
    public long getSize() {
        return size;
    }

}

