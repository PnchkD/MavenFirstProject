package by.iba.util;

import by.iba.dto.req.UserSearchCriteriaReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserServiceUtil {

    public void checkDefaultValues(UserSearchCriteriaReqDTO userSearchCriteriaReqDTO) {
        if (Objects.isNull(userSearchCriteriaReqDTO.getPageNo())) {
            userSearchCriteriaReqDTO.setPageNo(0);
        }

        if (Objects.isNull(userSearchCriteriaReqDTO.getPageSize())) {
            userSearchCriteriaReqDTO.setPageSize(10);
        }

        if (Objects.isNull(userSearchCriteriaReqDTO.getSortBy())) {
            userSearchCriteriaReqDTO.setSortBy("id");
        }
    }

    public Pageable getPageable(UserSearchCriteriaReqDTO userSearchCriteriaReqDTO) {
        return PageRequest.of(userSearchCriteriaReqDTO.getPageNo(),
                userSearchCriteriaReqDTO.getPageSize(),
                getSort(userSearchCriteriaReqDTO));
    }

    private Sort getSort(UserSearchCriteriaReqDTO userSearchCriteriaReqDTO) {
        if (userSearchCriteriaReqDTO.isDesc()) {
            return Sort.by(userSearchCriteriaReqDTO.getSortBy()).descending();
        }
        return Sort.by(userSearchCriteriaReqDTO.getSortBy());
    }

}
