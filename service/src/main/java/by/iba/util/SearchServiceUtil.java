package by.iba.util;

import by.iba.dto.req.user.SearchCriteriaReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SearchServiceUtil {

    public void checkDefaultValues(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        if (Objects.isNull(searchCriteriaReqDTO.getPageNo())) {
            searchCriteriaReqDTO.setPageNo(0);
        }

        if (Objects.isNull(searchCriteriaReqDTO.getPageSize())) {
            searchCriteriaReqDTO.setPageSize(10);
        }

        if (Objects.isNull(searchCriteriaReqDTO.getSortBy())) {
            searchCriteriaReqDTO.setSortBy("id");
        }
    }

    public Pageable getPageable(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        return PageRequest.of(searchCriteriaReqDTO.getPageNo(),
                searchCriteriaReqDTO.getPageSize(),
                getSort(searchCriteriaReqDTO));
    }

    private Sort getSort(SearchCriteriaReqDTO searchCriteriaReqDTO) {
        if (searchCriteriaReqDTO.isDesc()) {
            return Sort.by(searchCriteriaReqDTO.getSortBy()).descending();
        }
        return Sort.by(searchCriteriaReqDTO.getSortBy());
    }

}
