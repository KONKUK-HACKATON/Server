package com.KURUSH.KUFOREINER.global.security;

import com.KURUSH.KUFOREINER.global.exception.HttpExceptionCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JWTUtil {
    public static final String BEARER_TYPE = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "RefreshToken";
    public static final String BEARER_PREFIX = "Bearer ";

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUserId(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("userid", String.class);
    }

    public String getPassword(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("password", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration()
                .before(new Date());
    }

    public String createJwt(String userid, String password, Long expiredMs) {

        return Jwts.builder()
                .claim("userid", userid)
                .claim("password", password)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(String userid, String password, Long expiredMs) {

        String refreshToken = Jwts.builder()
                .claim("userid", userid)
                .claim("password", password)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();

        return refreshToken;
    }



    // Request Header에 Refresh Token 정보를 추출하는 메서드
    public String getRefreshToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(REFRESH_HEADER);
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(HttpServletRequest request) {
        try {
            String token = extractHeader(request);
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new JwtException(HttpExceptionCode.EXPIRED_TOKEN.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {//토큰이 존재하지 않을 경우
            throw new JwtException(HttpExceptionCode.JWT_NOT_FOUND.getMessage());
        } catch (SignatureException e) {//토큰이 임의의 값으로 변경된 경우
            throw new JwtException(HttpExceptionCode.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) { //토큰 길이나 형식이 다른 경우
            throw new JwtException(HttpExceptionCode.UNSUPPORTED_TOKEN.getMessage());
        }

        return true;

    }

    public static String extractHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.split(" ")[1];
        return token;
    }
}
