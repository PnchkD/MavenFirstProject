package by.iba;

import by.iba.dto.req.car.CarDescriptionReqDTO;
import by.iba.dto.req.car.CarReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.car.CarDTO;

import java.util.List;

public interface CarService {

    CarDTO save(CarReqDTO carReqDTO);

    List<CarDTO> getAll(SearchCriteriaReqDTO searchCriteriaReqDTO);

    CarDTO getById(Long id);

    CarDTO addDescription(Long id, CarDescriptionReqDTO carDescriptionReqDTO);

    void deleteById(Long id);

}
