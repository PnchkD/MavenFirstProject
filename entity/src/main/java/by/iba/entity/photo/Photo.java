package by.iba.entity.photo;

import by.iba.entity.AbstractEntity;
import by.iba.entity.car.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "photos")
@Getter
@Setter
@NoArgsConstructor
public class Photo extends AbstractEntity {

    @Column(name = "url", length = 90_00_000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
