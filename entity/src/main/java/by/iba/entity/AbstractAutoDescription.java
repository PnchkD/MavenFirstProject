package by.iba.entity;

import by.iba.entity.AbstractEntity;
import by.iba.entity.auto.AutoBodyType;
import by.iba.entity.auto.AutoDriveUnit;
import by.iba.entity.auto.AutoState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAutoDescription extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Integer price;

    @Column(name = "year_of_issue")
    private LocalDate yearOfIssue;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "drive_unit")
    private AutoDriveUnit driveUnit;

    @Column(name = "body_type")
    private AutoBodyType bodyType;

    @Column(name = "engine_capacity")
    private String engineCapacity;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "state")
    private AutoState state;

    @Column(name = "date_of_post")
    private LocalDateTime dateOfPost;

}
