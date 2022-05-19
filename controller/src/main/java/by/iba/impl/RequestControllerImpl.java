package by.iba.impl;

import by.iba.RequestController;
import by.iba.RequestService;
import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.request.RequestDTO;
import by.iba.dto.resp.request.RequestsDTO;
import by.iba.helper.ControllerHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class RequestControllerImpl implements RequestController {

    private final RequestService requestService;

    @Override
    public ResponseEntity<RequestsDTO> getRequests(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        List<RequestDTO> requests = requestService.getAll(searchCriteriaReqDTO);

        return ResponseEntity
                .ok()
                .body(new RequestsDTO(requests));
    }

    @Override
    public ResponseEntity<RequestsDTO> getRequestsByUser(@PathVariable Long id) {
        List<RequestDTO> requests = requestService.getAllByUser(id);

        return ResponseEntity
                .ok()
                .body(new RequestsDTO(requests));
    }

    @Override
    public ResponseEntity<RequestDTO> createRequest(@RequestBody @Valid RequestReqDTO requestReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        RequestDTO request = requestService.save(requestReqDTO);

        return ResponseEntity
                .ok()
                .body(request);
    }

    @Override
    public ResponseEntity<RespStatusDTO> deleteRequest(@PathVariable Long id) {
        requestService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("Request is successfully deleted!"));
    }

}
