package by.iba.impl;

import by.iba.DepartureController;
import by.iba.DepartureService;
import by.iba.dto.req.departure.DepartureReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.departure.DepartureDTO;
import by.iba.dto.resp.departure.DeparturesDTO;
import by.iba.dto.resp.request.RequestDTO;
import by.iba.dto.resp.request.RequestsDTO;
import by.iba.helper.ControllerHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class DepartureControllerImpl implements DepartureController {

    private final DepartureService departureService;

    public ResponseEntity<DepartureDTO> createDeparture(@RequestBody @Valid DepartureReqDTO departureReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        DepartureDTO departure = departureService.save(departureReqDTO);

        return ResponseEntity
                .status(200)
                .body(departure);
    }

    @Override
    public ResponseEntity<DeparturesDTO> getDepartures() {
        List<DepartureDTO> departures = departureService.getAll();

        return ResponseEntity
                .ok()
                .body(new DeparturesDTO(departures));
    }

}
