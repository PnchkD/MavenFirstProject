package by.iba.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSortCriteriaReqDTO {

    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private boolean desc;
}
