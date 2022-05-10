package by.iba.dto.req.brand;

import by.iba.dto.BaseAbstractReq;
import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandReqDTO extends BaseAbstractReq {

    @NotBlank(message = "Name cannot be empty")
    @Size(message = "Length is too large", max = ReqValidation.MAX_AUTO_NAME_LENGTH)
    private String name;

}
