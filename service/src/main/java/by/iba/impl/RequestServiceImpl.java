package by.iba.impl;

import by.iba.BrandService;
import by.iba.RequestService;
import by.iba.dto.req.request.RequestReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.request.RequestDTO;
import by.iba.entity.car.Car;
import by.iba.entity.car.CarBrand;
import by.iba.entity.car.CarDriveUnit;
import by.iba.entity.request.Request;
import by.iba.mapper.RequestMapper;
import by.iba.repository.RequestRepository;
import by.iba.specification.request.RequestSpecificationsBuilder;
import by.iba.util.SearchServiceUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final SearchServiceUtil searchServiceUtil;

    @Override
    public List<RequestDTO> getAll(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        searchServiceUtil.checkDefaultValues(searchCriteriaReqDTO);
        RequestSpecificationsBuilder builder = new RequestSpecificationsBuilder();

        if (Objects.nonNull(searchCriteriaReqDTO.getSearch())) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)(\\w+?),");
            Matcher matcher = pattern.matcher(searchCriteriaReqDTO.getSearch() + ",");

            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }

        }

        Specification<Request> specification = builder.build();

        Pageable paging = searchServiceUtil.getPageable(searchCriteriaReqDTO);

        Page<Request> pagedResult = requestRepository.findAll(specification, paging);

        return pagedResult.getContent()
                .stream()
                .map(requestMapper::fillInDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> getAllByUser(Long id) {
        return requestRepository.findAllByUserId(id)
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

    @Override
    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }

}
