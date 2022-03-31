package by.iba.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
}
