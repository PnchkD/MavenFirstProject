package by.iba;

import by.iba.dto.req.departure.DepartureReqDTO;
import by.iba.dto.resp.departure.DepartureDTO;
import by.iba.dto.resp.departure.DeparturesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/autopicker/departures")
@CrossOrigin(origins = "*")
public interface DepartureController {

    @PostMapping(value = "/create")
    ResponseEntity<DepartureDTO> createDeparture(@RequestBody @Valid DepartureReqDTO departureReqDTO, BindingResult bindingResult);

    @GetMapping("/")
    ResponseEntity<DeparturesDTO> getDepartures();

}
