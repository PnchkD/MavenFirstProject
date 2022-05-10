package by.iba.dto.resp.car;

import by.iba.dto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO extends AbstractDTO {

    private String type;
    private String brand;
    private String color;
    private Integer price;
    private Integer yearOfIssue;
    private Double mileage;
    private String engineType;
    private String driveUnit;
    private String bodyType;
    private String engineCapacity;
    private String transmission;
    private String state;
    private LocalDateTime dateOfPost;
    private String name;
    private String description;
    private List<String> photos;
    private String inspectionStatus;

}
