package by.iba.entity.departure;

import by.iba.entity.AbstractEntity;
import by.iba.entity.car.Car;
import by.iba.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "departures")
@Getter
@Setter
@NoArgsConstructor
public class Departure extends AbstractEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "date_of_departure")
    private LocalDateTime dateOfDeparture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity autoPicker;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Car car;

}
