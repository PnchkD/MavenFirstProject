package by.iba.dto.req.request;

import by.iba.dto.BaseAbstractReq;
import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReqDTO extends BaseAbstractReq {

    @Size(message = "Type is too large", max = ReqValidation.MAX_AUTO_TYPE_LENGTH)
    private String type;

    @Size(message = "Brand is too large", max = ReqValidation.MAX_AUTO_BRAND_LENGTH)
    private String brand;

    @Size(message = "Color is too large", max = ReqValidation.MAX_AUTO_COLOR_LENGTH)
    private String color;

    private Integer price;

    private String yearOfIssue;

    private Double mileage;

    @Size(message = "Color is too large", max = ReqValidation.MAX_AUTO_ENGINE_TYPE_LENGTH)
    private String engineType;

    @Size(message = "Drive unit is too large", max = ReqValidation.MAX_AUTO_DRIVE_UNIT_LENGTH)
    private String driveUnit;

    @Size(message = "Body type is too large", max = ReqValidation.MAX_AUTO_BODY_TYPE_LENGTH)
    private String bodyType;

    private String engineCapacity;

    @Size(message = "Transmission is too large", max = ReqValidation.MAX_AUTO_TRANSMISSION_LENGTH)
    private String transmission;

    @Size(message = "State is too large", max = ReqValidation.MAX_AUTO_STATE_LENGTH)
    private String state;

    private String wishes;

    @Size(message = "Length is too large", max = ReqValidation.MAX_COUNTRY_NAME_LENGTH)
    private String country;

    @Size(message = "Length is too large", max = ReqValidation.MAX_CITY_NAME_LENGTH)
    private String city;

    @NotNull
    private Long userId;

}
