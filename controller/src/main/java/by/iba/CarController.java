package by.iba;

import by.iba.dto.req.car.CarDescriptionReqDTO;
import by.iba.dto.req.car.CarReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.req.user.UserPersonalDataReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.car.CarDTO;
import by.iba.dto.resp.car.CarsDTO;
import by.iba.dto.resp.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/autopicker/cars")
@CrossOrigin(origins = "*")
public interface CarController {

    @GetMapping("/")
    ResponseEntity<CarsDTO> getCars(SearchCriteriaReqDTO searchCriteriaReqDTO);

    @GetMapping("/{id}")
    ResponseEntity<CarDTO> getCar(@PathVariable Long id);

    @PostMapping(value = "/create")
    ResponseEntity<CarDTO> createCar(@RequestBody @Valid CarReqDTO carReqDTO, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<RespStatusDTO> deleteCar(@PathVariable Long id);

    @PatchMapping("/{id}")
    ResponseEntity<CarDTO> updateDescription(@PathVariable Long id, @RequestBody @Valid CarDescriptionReqDTO cardescriptionReqDTO, BindingResult bindingResult);

}
