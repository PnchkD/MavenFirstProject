package by.iba.entity.car;

import by.iba.entity.AbstractCarDescription;
import by.iba.entity.photo.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends AbstractCarDescription {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "car")
    private Set<Photo> photos = new HashSet<>();

    @Column(name = "inspection_status")
    private CarInspectionStatus inspectionStatus;

}
