package by.iba.dto.req;

import by.iba.dto.BaseAbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoForAmazonReq extends BaseAbstractReq {

    private String name;

    private String path;


}

