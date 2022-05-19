package by.iba.mapper;

import by.iba.dto.req.departure.DepartureReqDTO;
import by.iba.dto.resp.departure.DepartureDTO;
import by.iba.entity.car.*;
import by.iba.entity.departure.Departure;
import by.iba.entity.user.UserEntity;
import by.iba.exception.ResourceNotFoundException;
import by.iba.repository.CarRepository;
import by.iba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DepartureMapper {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarMapper carMapper;
    private final UserMapper userMapper;

    public Departure fillFromRespDTO(DepartureReqDTO departureReqDTO){
        Departure departure = new Departure();

        if (Objects.nonNull(departureReqDTO.getDescription())) {
            departure.setDescription(departureReqDTO.getDescription());
        }

        if (Objects.nonNull(departureReqDTO.getDateOfDeparture())) {
            departure.setDateOfDeparture(LocalDate.parse(departureReqDTO.getDateOfDeparture()));
        }

        if (Objects.nonNull(departureReqDTO.getCarId())) {
            Car car = carRepository.findById(departureReqDTO.getCarId())
                    .orElseThrow(() -> new ResourceNotFoundException("CAR_HAS_BEEN_NOT_FOUND"));
            departure.setCar(car);
        }

        if (Objects.nonNull(departureReqDTO.getUserId())) {
            UserEntity autopicker = userRepository.findById(departureReqDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("USER_HAS_BEEN_NOT_FOUND"));
            departure.setAutoPicker(autopicker);
        }


        return departure;
    }

    public DepartureDTO fillFromInDTO(Departure departure){
        DepartureDTO departureDTO = new DepartureDTO();

        if(Objects.nonNull(departure.getId())) {
            departureDTO.setId(departure.getId());
        }

        if(Objects.nonNull(departure.getDateOfCreation())) {
            departureDTO.setDateOfCreation(departure.getDateOfCreation());
        }

        if(Objects.nonNull(departure.getDateOfLastUpdate())) {
            departureDTO.setDateOfLastUpdate(departure.getDateOfLastUpdate());
        }

        if(Objects.nonNull(departure.getDescription())) {
            departureDTO.setDescription(departure.getDescription());
        }

        if(Objects.nonNull(departure.getDateOfDeparture())) {
            departureDTO.setDateOfDeparture(departure.getDateOfDeparture());
        }

        if(Objects.nonNull(departure.getCar())) {
            departureDTO.setCar(carMapper.fillFromInDTO(departure.getCar()));
        }

        if(Objects.nonNull(departure.getAutoPicker())) {
            departureDTO.setAutoPicker(userMapper.fillFromInDTO(departure.getAutoPicker()));
        }

        return departureDTO;
    }

}
