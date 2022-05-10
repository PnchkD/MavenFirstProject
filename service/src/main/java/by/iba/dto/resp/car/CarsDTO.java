package by.iba.dto.resp.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarsDTO {

    private List<CarDTO> carsDTO;

}
