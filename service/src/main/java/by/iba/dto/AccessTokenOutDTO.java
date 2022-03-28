package by.iba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenOutDTO {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
}