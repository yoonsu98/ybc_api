package com.yoonsu.ybc.common.utils;

import com.yoonsu.ybc.login.domain.response.UserResponse;
import io.jsonwebtoken.*;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.yoonsu.ybc.common.utils
 * fileName       : JwtProvider
 * author         : yoons
 * date           : 2024-01-02
 * description    : jwt 생성
 */
public class JwtProvider {
    @Value("${security.key}")
    private static String securityKey;
    private Long accessExpiredTime = 1000 * 60 * 30L; //30분
    private Long refreshExpiredTime = 1000 * 60 * 60 * 24 * 30L; //한 달

    /**
     * access token 생성
     *
     * @param kakaoId
     * @return
     * @throws Exception
     */
    public String createAccessToken(String kakaoId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpiredTime);

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
    public static String getSubject(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(securityKey)).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * 유효한 토큰인지 확인
     *
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        Date expiration = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody().getExpiration();
        if (expiration.before(new Date())) {
            return false;
        }
        return true;
    }
}
