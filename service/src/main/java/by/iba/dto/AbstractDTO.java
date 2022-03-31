package by.iba.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractDTO {

    private Long id;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastUpdate;

}
