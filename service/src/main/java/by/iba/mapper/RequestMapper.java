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
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final CarBrandRepository brandRepository;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;
    private final CommentMapper commentMapper;

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

        if(Objects.nonNull(request.getBrand())) {
            requestDTO.setBrand(request.getBrand().getName());
        }

        if(Objects.nonNull(request.getColor())) {
            requestDTO.setColor(request.getColor());
        }

        if(Objects.nonNull(request.getPrice())) {
            requestDTO.setPrice(request.getPrice());
        }

        if(Objects.nonNull(request.getYearOfIssue())) {
            requestDTO.setYearOfIssue(request.getYearOfIssue().toString());
        }

        if(Objects.nonNull(request.getMileage())) {
            requestDTO.setMileage(request.getMileage());
        }

        if(Objects.nonNull(request.getEngineType())) {
            requestDTO.setEngineType(request.getEngineType());
        }

        if(Objects.nonNull(request.getDriveUnit())) {
            requestDTO.setDriveUnit(request.getDriveUnit().name());
        }

        if(Objects.nonNull(request.getBodyType())) {
            requestDTO.setBodyType(request.getBodyType().name());
        }

        if(Objects.nonNull(request.getEngineCapacity())) {
            requestDTO.setEngineCapacity(request.getEngineCapacity());
        }

        if(Objects.nonNull(request.getTransmission())) {
            requestDTO.setTransmission(request.getTransmission());
        }

        if(Objects.nonNull(request.getState())) {
            requestDTO.setState(request.getState().name());
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

        if(!request.getTickets().isEmpty()) {
            requestDTO.setTickets(request.getTickets()
                    .stream()
                    .map(ticketMapper::fillFromInDTO)
                    .collect(Collectors.toList()));
        }

        if(!request.getComments().isEmpty()) {
            requestDTO.setComments(request.getComments()
                    .stream()
                    .map(commentMapper::fillInDTO)
                    .collect(Collectors.toList()));
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

        if(Objects.nonNull(requestReqDTO.getYearOfIssue()) && !Objects.equals(requestReqDTO.getYearOfIssue(), "")) {
            request.setYearOfIssue(LocalDate.parse(requestReqDTO.getYearOfIssue()));
        }

        if(Objects.nonNull(requestReqDTO.getMileage())) {
            request.setMileage(requestReqDTO.getMileage());
        }

        if(Objects.nonNull(requestReqDTO.getEngineType())) {
            request.setEngineType(requestReqDTO.getEngineType());
        }

        if(Objects.nonNull(requestReqDTO.getDriveUnit()) && !Objects.equals(requestReqDTO.getDriveUnit(), "")) {
            request.setDriveUnit(CarDriveUnit.valueOf(requestReqDTO.getDriveUnit()));
        }

        if(Objects.nonNull(requestReqDTO.getBodyType()) && !Objects.equals(requestReqDTO.getBodyType(), "")) {
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
