package by.iba.dto.req.ticket;

import by.iba.dto.BaseAbstractReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketReqDTO extends BaseAbstractReq {

    private String name;
    private String description;
    private String dateOfDeparture;
    private Long userId;
    private Long carId;
    private Long requestId;
}
