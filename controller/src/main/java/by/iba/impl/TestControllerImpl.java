package by.iba.impl;

import by.iba.TestController;
import by.iba.dto.resp.RespStatusDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestControllerImpl implements TestController {

    @Override
    public ResponseEntity<RespStatusDTO> ecsGetEndpoint() {
        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("HELLO WORLD!"));
    }

}
