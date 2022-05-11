package by.iba.impl;

import by.iba.CarService;
import by.iba.PhotoService;
import by.iba.dto.req.car.CarDescriptionReqDTO;
import by.iba.dto.req.car.CarReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.car.CarDTO;
import by.iba.entity.car.Car;
import by.iba.entity.user.UserEntity;
import by.iba.exception.ResourceNotFoundException;
import by.iba.mapper.CarMapper;
import by.iba.repository.CarRepository;
import by.iba.specification.car.CarSpecificationsBuilder;
import by.iba.specification.user.UserSpecificationsBuilder;
import by.iba.util.SearchServiceUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final SearchServiceUtil searchServiceUtil;
    private final PhotoService photoService;

    @Override
    @Transactional
    public CarDTO save(CarReqDTO carReqDTO) {

        Car car = carMapper.fillFromRespDTO(carReqDTO);
        car.setDateOfPost(LocalDateTime.now());

        car = carRepository.save(car);

        car = carMapper.fillPhotosFromDTO(car, carReqDTO.getPhotos());

        return carMapper.fillFromInDTO(car);
    }

    @Override
    @Transactional
    public List<CarDTO> getAll(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        searchServiceUtil.checkDefaultValues(searchCriteriaReqDTO);
        CarSpecificationsBuilder builder = new CarSpecificationsBuilder();

        if (Objects.nonNull(searchCriteriaReqDTO.getSearch())) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)(\\w+?),");
            Matcher matcher = pattern.matcher(searchCriteriaReqDTO.getSearch() + ",");

            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }

        }

        Specification<Car> specification = builder.build();

        Pageable paging = searchServiceUtil.getPageable(searchCriteriaReqDTO);

        Page<Car> pagedResult = carRepository.findAll(specification, paging);

        return pagedResult.getContent()
                .stream()
                .map(carMapper::fillFromInDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getById(Long id) {
        return carMapper.fillFromInDTO(carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CAR_HAS_BEEN_NOT_FOUND")));
    }

    @Override
    public void deleteById(Long id) {
        photoService.deleteAllByCarId(id);
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CarDTO addDescription(Long id, CarDescriptionReqDTO carDescriptionReqDTO) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CAR_HAS_BEEN_NOT_FOUND"));

        if(Objects.nonNull(carDescriptionReqDTO.getDescription())) {
            car.setDescription(carDescriptionReqDTO.getDescription());
        }

        car = carRepository.save(car);

        return carMapper.fillFromInDTO(car);
    }


}
