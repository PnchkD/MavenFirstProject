package by.iba.impl;

import by.iba.DepartureService;
import by.iba.dto.req.departure.DepartureReqDTO;
import by.iba.dto.resp.departure.DepartureDTO;
import by.iba.entity.departure.Departure;
import by.iba.exception.ResourceNotFoundException;
import by.iba.mapper.DepartureMapper;
import by.iba.repository.DepartureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartureServiceImpl implements DepartureService {

    private final DepartureRepository departureRepository;
    private final DepartureMapper departureMapper;
    
    @Override
    @Transactional
    public DepartureDTO save(DepartureReqDTO departureReqDTO) {

        Departure departure = departureMapper.fillFromRespDTO(departureReqDTO);

        departure = departureRepository.save(departure);

        return departureMapper.fillFromInDTO(departure);
    }

    @Override
    @Transactional
    public List<DepartureDTO> getAll() {
        return departureRepository.findAll()
                .stream()
                .map(departureMapper::fillFromInDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        departureRepository.deleteById(id);
    }
    
}
