package by.iba.entity;

import by.iba.entity.car.CarBodyType;
import by.iba.entity.car.CarBrand;
import by.iba.entity.car.CarDriveUnit;
import by.iba.entity.car.CarState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCarDescription extends AbstractEntity {

    @Column(name = "type", length = 50)
    private String type;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrand brand;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "price", length = 15)
    private Integer price;

    @Column(name = "year_of_issue")
    private LocalDate yearOfIssue;

    @Column(name = "mileage")
    private Double mileage;

    @Column(name = "engine_type", length = 30)
    private String engineType;

    @Column(name = "drive_unit")
    private CarDriveUnit driveUnit;

    @Column(name = "body_type")
    private CarBodyType bodyType;

    @Column(name = "engine_capacity", length = 20)
    private String engineCapacity;

    @Column(name = "transmission", length = 50)
    private String transmission;

    @Column(name = "state")
    private CarState state;

    @Column(name = "date_of_post")
    private LocalDateTime dateOfPost;

}
