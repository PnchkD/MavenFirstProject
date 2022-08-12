package by.iba.dto.resp;

import by.iba.dto.AbstractDTO;
import by.iba.entity.DocumentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoForAmazonResp extends AbstractDTO {

    private Long userId;

    private String uniqueId;

    private String name;

    private String path;

    private Long size;

    private String externalKey;

    private String presignedUrl;

    private DocumentStatus status;

}
