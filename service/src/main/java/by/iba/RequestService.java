package by.iba;

import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.request.RequestDTO;

import java.util.List;

public interface RequestService {

    List<RequestDTO> getAll(SearchCriteriaReqDTO searchCriteriaReqDTO);

    List<RequestDTO> getAllByUser(Long id);

    RequestDTO save(RequestReqDTO requestReqDTO);

    void deleteById(Long id);

}
