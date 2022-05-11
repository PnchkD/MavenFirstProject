package by.iba.impl;

import by.iba.RequestService;
import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.resp.request.RequestDTO;
import by.iba.entity.request.Request;
import by.iba.mapper.RequestMapper;
import by.iba.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public List<RequestDTO> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(requestMapper::fillInDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RequestDTO save(RequestReqDTO requestReqDTO) {

        Request request = requestMapper.fillFromDTO(requestReqDTO);

        request = requestRepository.save(request);

        return requestMapper.fillInDTO(request);
    }
}
