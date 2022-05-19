package by.iba.dto.resp.departure;

import by.iba.dto.AbstractDTO;
import by.iba.dto.resp.car.CarDTO;
import by.iba.dto.resp.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartureDTO extends AbstractDTO {

    private String description;
    private LocalDate dateOfDeparture;
    private UserDTO autoPicker;
    private CarDTO car;

}
