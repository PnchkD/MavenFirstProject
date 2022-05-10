package by.iba.entity.car;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "car_brands")
@Getter
@Setter
@NoArgsConstructor
public class CarBrand extends AbstractEntity {

    @Column(name = "name", length = 100)
    private String name;

}