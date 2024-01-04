package com.yoonsu.ybc.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;

/**
 * packageName    : com.yoonsu.ybc.common.utils
 * fileName       : JwtProvider
 * author         : yoons
 * date           : 2024-01-02
 * description    : jwt 생성
 */
@Component
public class JwtProvider {

    @Value("${security.key}")
    private String securityKey;

    private Long accessExpiredTime = 1000 * 60 * 30L; //30분
    private Long refreshExpiredTime = 1000 * 60 * 60 * 24 * 30L; //한 달

    /**
     * access token 생성
     *
     * @param kakaoId
     * @return
     * @throws Exception
     */
    public String createAccessToken(String kakaoId) throws JsonProcessingException {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpiredTime);

        // Build the JWT
        String token = Jwts.builder()
                .setSubject(kakaoId)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();

        return token;
    }

    /**
     * refresh token 생성
     * @return
     * @throws Exception
     */
    public String createRefreshToken() {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpiredTime);

        String token = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();

        return token;
    }

    /**
     * token에서 subject 추출
     * @param token
     * @return
     */
    public String getSubject(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(securityKey);
        Claims claims = Jwts.parser().setSigningKey(keyBytes).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 유효한 토큰인지 확인
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        Date expiration = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody().getExpiration();
        if (expiration.before(new Date())) {
            return false;
        }
        return true;
    }
}
