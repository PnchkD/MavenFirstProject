package by.iba.dto.req.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteriaReqDTO {

    private String search;
    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private boolean desc;

}
