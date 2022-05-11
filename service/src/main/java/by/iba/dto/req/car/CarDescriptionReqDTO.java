package by.iba.dto.req.car;

import by.iba.dto.BaseAbstractReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDescriptionReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Description cannot be empty")
    private String description;

}
