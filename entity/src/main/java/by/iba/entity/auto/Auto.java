package by.iba.entity.auto;

import by.iba.entity.AbstractAutoDescription;
import by.iba.entity.user.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "autos")
@Getter
@Setter
@NoArgsConstructor
public class Auto extends AbstractAutoDescription {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private Photo photo;

    @Column(name = "status")
    private AutoStatus status;

}
