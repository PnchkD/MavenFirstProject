package by.iba.mapper;

import by.iba.BrandService;
import by.iba.PhotoService;
import by.iba.dto.req.car.CarReqDTO;
import by.iba.dto.resp.car.CarDTO;
import by.iba.entity.car.*;
import by.iba.entity.photo.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarMapper {

    private final PhotoService photoService;
    private final BrandService brandService;

    public Car fillFromRespDTO(CarReqDTO carReqDTO){
        Car car = new Car();

        if (Objects.nonNull(carReqDTO.getName())) {
            car.setName(carReqDTO.getName());
        }

        if (Objects.nonNull(carReqDTO.getDescription())) {
            car.setDescription(carReqDTO.getDescription());
        }

        if (Objects.nonNull(carReqDTO.getStatus())) {
            car.setInspectionStatus(CarInspectionStatus.valueOf(carReqDTO.getStatus()));
        } else {
            car.setInspectionStatus(CarInspectionStatus.NOT_CHECKED);
        }

        if (Objects.nonNull(carReqDTO.getType())) {
            car.setType(carReqDTO.getType());
        }

        if (Objects.nonNull(carReqDTO.getBrand())) {
            car.setBrand(brandService.getByName(carReqDTO.getBrand()));
        }

        if (Objects.nonNull(carReqDTO.getColor())) {
            car.setColor(carReqDTO.getColor());
        }

        if (Objects.nonNull(carReqDTO.getPrice())) {
            car.setPrice(carReqDTO.getPrice());
        }

        if (Objects.nonNull(carReqDTO.getYearOfIssue())) {
            car.setYearOfIssue(LocalDate.parse(carReqDTO.getYearOfIssue()));
        }

        if (Objects.nonNull(carReqDTO.getMileage())) {
            car.setMileage(carReqDTO.getMileage());
        }

        if (Objects.nonNull(carReqDTO.getEngineType())) {
            car.setEngineType(carReqDTO.getEngineType());
        }

        if (Objects.nonNull(carReqDTO.getDriveUnit())) {
            car.setDriveUnit(CarDriveUnit.valueOf(carReqDTO.getDriveUnit()));
        }

        if (Objects.nonNull(carReqDTO.getBodyType())) {
            car.setBodyType(CarBodyType.valueOf(carReqDTO.getBodyType()));
        }

        if (Objects.nonNull(carReqDTO.getEngineCapacity())) {
            car.setEngineCapacity(carReqDTO.getEngineCapacity());
        }

        if (Objects.nonNull(carReqDTO.getTransmission())) {
            car.setTransmission(carReqDTO.getTransmission());
        }

        if (Objects.nonNull(carReqDTO.getState())) {
            car.setState(CarState.valueOf(carReqDTO.getState()));
        }

        return car;
    }

    public Car fillPhotosFromDTO(Car car, List<String> photos){

        if (!photos.isEmpty()) {
            car.setPhotos(photos
                    .stream()
                    .map(url -> photoService.save(url, car))
                    .collect(Collectors.toSet()));
        }

        return car;
    }

    public CarDTO fillFromInDTO(Car car){
        CarDTO carDTO = new CarDTO();

        if(Objects.nonNull(car.getId())) {
            carDTO.setId(car.getId());
        }

        if(Objects.nonNull(car.getDateOfCreation())) {
            carDTO.setDateOfCreation(car.getDateOfCreation());
        }

        if(Objects.nonNull(car.getDateOfLastUpdate())) {
            carDTO.setDateOfLastUpdate(car.getDateOfLastUpdate());
        }

        if(Objects.nonNull(car.getType())) {
            carDTO.setType(car.getType());
        }

        if(Objects.nonNull(car.getBrand())) {
            carDTO.setBrand(car.getBrand().getName());
        }

        if(Objects.nonNull(car.getColor())) {
            carDTO.setColor(car.getColor());
        }

        if(Objects.nonNull(car.getPrice())) {
            carDTO.setPrice(car.getPrice());
        }

        if(Objects.nonNull(car.getYearOfIssue())) {
            carDTO.setYearOfIssue(car.getYearOfIssue().getYear());
        }

        if(Objects.nonNull(car.getMileage())) {
            carDTO.setMileage(car.getMileage());
        }

        if(Objects.nonNull(car.getEngineType())) {
            carDTO.setEngineType(car.getEngineType());
        }

        if(Objects.nonNull(car.getDriveUnit())) {
            carDTO.setDriveUnit(car.getDriveUnit().toString());
        }

        if(Objects.nonNull(car.getBodyType())) {
            carDTO.setBodyType(car.getBodyType().toString());
        }

        if(Objects.nonNull(car.getEngineCapacity())) {
            carDTO.setEngineCapacity(car.getEngineCapacity());
        }

        if(Objects.nonNull(car.getTransmission())) {
            carDTO.setTransmission(car.getTransmission());
        }

        if(Objects.nonNull(car.getState())) {
            carDTO.setState(car.getState().toString());
        }

        if(Objects.nonNull(car.getState())) {
            carDTO.setState(car.getState().toString());
        }

        if(Objects.nonNull(car.getDateOfPost())) {
            carDTO.setDateOfPost(car.getDateOfPost());
        }

        if(Objects.nonNull(car.getName())) {
            carDTO.setName(car.getName());
        }

        if(Objects.nonNull(car.getInspectionStatus())) {
            carDTO.setInspectionStatus(car.getInspectionStatus().toString());
        }

        if(Objects.nonNull(car.getPhotos())) {
            carDTO.setPhotos(car.getPhotos()
                    .stream()
                    .map(Photo::getImageUrl)
                    .collect(Collectors.toList()));
        }

        if(Objects.nonNull(car.getDescription())) {
            carDTO.setDescription(car.getDescription());
        }

        return carDTO;
    }

}
