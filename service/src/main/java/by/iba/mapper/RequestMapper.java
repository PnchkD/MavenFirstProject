package by.iba.mapper;

import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.resp.request.RequestDTO;
import by.iba.entity.car.CarBodyType;
import by.iba.entity.car.CarBrand;
import by.iba.entity.car.CarDriveUnit;
import by.iba.entity.car.CarState;
import by.iba.entity.region.Region;
import by.iba.entity.request.Request;
import by.iba.entity.user.UserEntity;
import by.iba.exception.ResourceNotFoundException;
import by.iba.repository.CarBrandRepository;
import by.iba.repository.RegionRepository;
import by.iba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final CarBrandRepository brandRepository;
    private final UserMapper userMapper;

    public RequestDTO fillInDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();

        if(Objects.nonNull(request.getId())) {
            requestDTO.setId(request.getId());
        }

        if(Objects.nonNull(request.getDateOfCreation())) {
            requestDTO.setDateOfCreation(request.getDateOfCreation());
        }

        if(Objects.nonNull(request.getDateOfLastUpdate())) {
            requestDTO.setDateOfLastUpdate(request.getDateOfLastUpdate());
        }

        if(Objects.nonNull(request.getWishes())) {
            requestDTO.setWishes(request.getWishes());
        }

        if(Objects.nonNull(request.getRegion().getCountry())) {
            requestDTO.setCountry(request.getRegion().getCountry());
        }

        if(Objects.nonNull(request.getRegion().getCity())) {
            requestDTO.setCity(request.getRegion().getCity());
        }

        if(Objects.nonNull(request.getUser())) {
            requestDTO.setFromUser(userMapper.fillFromInDTO(request.getUser()));
        }

        return requestDTO;
    }

    public Request fillFromDTO(RequestReqDTO requestReqDTO) {
        Request request = new Request();

        if(Objects.nonNull(requestReqDTO.getType())) {
            request.setType(requestReqDTO.getType());
        }

        if(Objects.nonNull(requestReqDTO.getBrand())) {
            CarBrand brand = brandRepository.findCarBrandByName(requestReqDTO.getBrand())
                            .orElseThrow(() -> new ResourceNotFoundException("CAR_BRAND_HAS_BEEN_NOT_FOUND"));
            request.setBrand(brand);
        }

        if(Objects.nonNull(requestReqDTO.getColor())) {
            request.setColor(requestReqDTO.getColor());
        }

        if(Objects.nonNull(requestReqDTO.getPrice())) {
            request.setPrice(requestReqDTO.getPrice());
        }

        if(Objects.nonNull(requestReqDTO.getYearOfIssue())) {
            request.setYearOfIssue(LocalDate.parse(requestReqDTO.getYearOfIssue()));
        }

        if(Objects.nonNull(requestReqDTO.getMileage())) {
            request.setMileage(requestReqDTO.getMileage());
        }

        if(Objects.nonNull(requestReqDTO.getEngineType())) {
            request.setEngineType(requestReqDTO.getEngineType());
        }

        if(Objects.nonNull(requestReqDTO.getDriveUnit())) {
            request.setDriveUnit(CarDriveUnit.valueOf(requestReqDTO.getDriveUnit()));
        }

        if(Objects.nonNull(requestReqDTO.getBodyType())) {
            request.setBodyType(CarBodyType.valueOf(requestReqDTO.getBodyType()));
        }

        if(Objects.nonNull(requestReqDTO.getEngineCapacity())) {
            request.setEngineCapacity(requestReqDTO.getEngineCapacity());
        }

        if(Objects.nonNull(requestReqDTO.getTransmission())) {
            request.setTransmission(requestReqDTO.getTransmission());
        }

        if(Objects.nonNull(requestReqDTO.getState())) {
            request.setState(CarState.valueOf(requestReqDTO.getState()));
        }

        if(Objects.nonNull(requestReqDTO.getWishes())) {
            request.setWishes(requestReqDTO.getWishes());
        }

        if(Objects.nonNull(requestReqDTO.getCountry()) && Objects.nonNull(requestReqDTO.getCity()) ) {
            Optional<Region> region = regionRepository.findByCountryAndCity(requestReqDTO.getCountry(), requestReqDTO.getCity());
            if(region.isEmpty()) {
                Region newRegion = new Region();
                newRegion.setCity(requestReqDTO.getCity());
                newRegion.setCountry(requestReqDTO.getCountry());
                newRegion = regionRepository.save(newRegion);
                request.setRegion(newRegion);
            } else {
                request.setRegion(region.get());
            }
        }

        if(Objects.nonNull(requestReqDTO.getUserId())) {
            UserEntity user = userRepository.findById(requestReqDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("USER_HAS_BEEN_NOT_FOUND"));
            request.setUser(user);
        }

        return request;
    }

}
