package by.iba.filter;

import by.iba.entity.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    public static final String TOKEN_TYPE = "Bearer";
    public static final String SPACE = " ";
    public static final String BEARER_PREFIX = TOKEN_TYPE + SPACE;

    @Value("${security-token-app.auth.accessTokenSecret}")
    private String secretKey;
    @Value("${security-token-app.auth.accessTokenExpirationMsec}")
    private int expirationSeconds;

    @PostConstruct
    private void init(){
        Base64.Encoder encoder = Base64.getEncoder();
        secretKey = encoder.encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token){
        if(token == null || token.isEmpty()){
            return false;
        }

        return !Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public AccessToken generateToken(UserEntity user){
        String rolePrefix = "ROLE_";
        Claims claims = Jwts.claims()
                .setSubject(user.getLogin());
        List<String> roles = user.getRoles().stream()
                        .map(x -> rolePrefix + x.getName())
                        .collect(Collectors.toList());
        claims.put("roles", roles);
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());

        Date currentDate = new Date();
        Date expiration = new Date(currentDate.getTime() + expirationSeconds);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setIssuedAt(currentDate)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return new AccessToken(accessToken, TOKEN_TYPE, expirationSeconds);
    }

    public boolean isBearer(String token){
        return token.startsWith(BEARER_PREFIX);
    }

    public String removeBearerPrefix(String token){
        return token.replace(BEARER_PREFIX, "");
    }

    public String getLogin(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRole(String token){
        return (String) Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
}
