package by.iba.dto.req.car;

import by.iba.dto.BaseAbstractReq;
import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Name cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_AUTO_NAME_LENGTH)
    private String name;

    private List<String> photos;

    @NotBlank(message = "Auto's type cannot be empty")
    @Size(message = "Type is too large", max = ReqValidation.MAX_AUTO_TYPE_LENGTH)
    private String type;

    @NotBlank(message = "Auto's brand cannot be empty")
    @Size(message = "Brand is too large", max = ReqValidation.MAX_AUTO_BRAND_LENGTH)
    private String brand;

    @NotBlank(message = "Auto's color cannot be empty")
    @Size(message = "Color is too large", max = ReqValidation.MAX_AUTO_COLOR_LENGTH)
    private String color;

    @NotNull
    private Integer price;

    @NotBlank(message = "Auto's year of issue cannot be empty")
    private String yearOfIssue;

    @NotNull
    private Double mileage;

    @NotBlank(message = "Auto's engine type cannot be empty")
    @Size(message = "Color is too large", max = ReqValidation.MAX_AUTO_ENGINE_TYPE_LENGTH)
    private String engineType;

    @NotBlank(message = "Auto's drive unit cannot be empty")
    @Size(message = "Drive unit is too large", max = ReqValidation.MAX_AUTO_DRIVE_UNIT_LENGTH)
    private String driveUnit;

    @NotBlank(message = "Auto's body type cannot be empty")
    @Size(message = "Body type is too large", max = ReqValidation.MAX_AUTO_BODY_TYPE_LENGTH)
    private String bodyType;

    @NotBlank(message = "Auto's engine capacity cannot be empty")
    private String engineCapacity;

    @NotBlank(message = "Auto's transmission cannot be empty")
    @Size(message = "Transmission is too large", max = ReqValidation.MAX_AUTO_TRANSMISSION_LENGTH)
    private String transmission;

    @NotBlank(message = "Auto's state cannot be empty")
    @Size(message = "State is too large", max = ReqValidation.MAX_AUTO_STATE_LENGTH)
    private String state;

    private String status;

    private String description;

}

