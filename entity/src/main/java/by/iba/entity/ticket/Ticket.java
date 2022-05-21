package by.iba.entity.ticket;

import by.iba.entity.AbstractEntity;
import by.iba.entity.car.Car;
import by.iba.entity.photo.Photo;
import by.iba.entity.request.Request;
import by.iba.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
public class Ticket extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_of_departure")
    private LocalDate dateOfDeparture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity autoPicker;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

}
