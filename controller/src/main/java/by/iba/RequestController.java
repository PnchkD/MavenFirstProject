package by.iba;

import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.request.RequestDTO;
import by.iba.dto.resp.request.RequestsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/requests")
@CrossOrigin(origins = "*")
public interface RequestController {

    @GetMapping("/")
    ResponseEntity<RequestsDTO> getRequests(SearchCriteriaReqDTO searchCriteriaReqDTO);

    @GetMapping("/user/{id}")
    ResponseEntity<RequestsDTO> getRequestsByUser(@PathVariable Long id);

    @PostMapping(value = "/create")
    ResponseEntity<RequestDTO> createRequest(@RequestBody @Valid RequestReqDTO requestReqDTO, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<RespStatusDTO> deleteRequest(@PathVariable Long id);

}
