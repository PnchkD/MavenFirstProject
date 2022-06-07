package by.iba.entity.car;

import by.iba.entity.AbstractEntity;
import by.iba.entity.request.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "car_brands")
@Getter
@Setter
@NoArgsConstructor
public class CarBrand extends AbstractEntity {

    @Column(name = "name", length = 100)
    private String name;

}