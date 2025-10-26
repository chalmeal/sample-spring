package sample.context;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * <pre>
 * JWTコンテキストクラス
 * </pre>
 */
@Component
public class JwtProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final String issuer = "sampleIssuer";
    private final long expireSeconds = 3600;

    /**
     * <pre>
     * JWTの生成
     * </pre>
     * 
     * @param accountId
     * @param authority
     * @return
     */
    public String generateToken(String accountId, String authority) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + expireSeconds * 1000);

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(accountId)
                .claim("authority", authority)
                .setExpiration(expired)
                .signWith(key)
                .compact();
    }

    /**
     * <pre>
     * JWTの検証
     * </pre>
     * 
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
