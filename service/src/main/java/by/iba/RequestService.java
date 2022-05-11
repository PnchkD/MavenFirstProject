package by.iba;

import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.resp.request.RequestDTO;

import java.util.List;

public interface RequestService {

    List<RequestDTO> getAll();

    RequestDTO save(RequestReqDTO requestReqDTO);

}
