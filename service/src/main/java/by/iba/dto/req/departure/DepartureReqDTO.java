package by.iba.dto.req.departure;

import by.iba.dto.BaseAbstractReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartureReqDTO extends BaseAbstractReq {

    private String description;
    private String dateOfDeparture;
    private Long userId;
    private Long carId;
}
