package by.iba.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchCriteriaReqDTO {

    private String search;
    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private boolean desc;

}
