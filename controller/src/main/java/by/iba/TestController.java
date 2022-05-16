package by.iba;

import by.iba.dto.resp.RespStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/")
public interface TestController {

    @GetMapping
    ResponseEntity<RespStatusDTO> ecsGetEndpoint();

}
