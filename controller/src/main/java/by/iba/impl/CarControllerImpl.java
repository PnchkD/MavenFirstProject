package by.iba.impl;

import by.iba.CarController;
import by.iba.CarService;
import by.iba.dto.req.car.CarDescriptionReqDTO;
import by.iba.dto.req.car.CarReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.car.CarDTO;
import by.iba.dto.resp.car.CarsDTO;
import by.iba.dto.resp.user.UserDTO;
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
public class CarControllerImpl implements CarController {

    private final CarService carService;

    @Override
    public ResponseEntity<CarsDTO> getCars(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        List<CarDTO> cars = carService.getAll(searchCriteriaReqDTO);

        return ResponseEntity
                .ok()
                .body(new CarsDTO(cars));
    }

    @Override
    public ResponseEntity<CarDTO> getCar(@PathVariable Long id) {
        CarDTO car = carService.getById(id);

        return ResponseEntity
                .ok()
                .body(car);
    }

    @Override
    public ResponseEntity<CarDTO> createCar(@RequestBody @Valid CarReqDTO carReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CarDTO auto = carService.save(carReqDTO);

        return ResponseEntity
                .status(200)
                .body(auto);

    }

    @Override
    public ResponseEntity<RespStatusDTO> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("Car is successfully deleted"));
    }

    @Override
    public ResponseEntity<CarDTO> updateDescription(@PathVariable Long id, @RequestBody @Valid CarDescriptionReqDTO carDescriptionReqDTO, BindingResult bindingResult) {

        CarDTO car = carService.addDescription(id, carDescriptionReqDTO);

        return ResponseEntity
                .status(200)
                .body(car);
    }
}
