package by.iba.dto.resp.departure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeparturesDTO {

    private List<DepartureDTO> departures;

}
