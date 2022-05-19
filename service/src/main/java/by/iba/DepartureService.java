package by.iba;

import by.iba.dto.req.departure.DepartureReqDTO;
import by.iba.dto.resp.departure.DepartureDTO;

import java.util.List;

public interface DepartureService {

    DepartureDTO save(DepartureReqDTO departureReqDTO);

    List<DepartureDTO> getAll();

    void deleteById(Long id);

}