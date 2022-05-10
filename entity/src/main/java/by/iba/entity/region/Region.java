package by.iba.entity.region;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Getter
@Setter
@NoArgsConstructor
public class Region extends AbstractEntity {

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", length = 50)
    private String city;

}
