package com.yoonsu.ybc.api.kakao.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonsu.ybc.api.kakao.domain.response.KakaoResponse;
import com.yoonsu.ybc.common.utils.RestApiTemplate;
import com.yoonsu.ybc.config.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * packageName    : com.yoonsu.ybc.common.api.kakao
 * fileName       : KakaoService
 * author         : yoons
 * date           : 2023-12-16
 * description    : 카카오 로그인 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final RestApiTemplate restApiTemplate;
    @Value("${kakao.client.id}")
    private String clientId;
    @Value("${kakao.client.secret}")
    private String clientSecret;
    @Value("${kakao.grant.type}")
    private String grantType;
    @Value("${kakao.redirect.uri}")
    private String redirectUri;
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 카카오 토큰 발급
     * @param code
     * @return
     */
    public KakaoResponse getToken(String code) {
        KakaoResponse kakaoResponse = null;
        try {
            restApiTemplate.setHeader(MediaType.APPLICATION_FORM_URLENCODED, null);
            MultiValueMap<String, String> dataMap = new LinkedMultiValueMap<>();
            dataMap.add("code", code);
            dataMap.add("client_id", clientId);
            dataMap.add("redirect_url", redirectUri);
            dataMap.add("grant_type", grantType);
            dataMap.add("client_secret", clientSecret);
            Object response = restApiTemplate.post("https://kauth.kakao.com/oauth/token", dataMap);
            kakaoResponse = objectMapper.convertValue(response, KakaoResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return kakaoResponse;
    }

    /**
     * 카카오 토큰 정보 조회
     *
     * @param accessToken
     * @return
     */
    public String getTokenInfo(String accessToken) {
        String kakaoId = null;
        try {
            restApiTemplate.setHeader(accessToken);
            Object response = restApiTemplate.get("https://kapi.kakao.com/v1/user/access_token_info");
            Map<String, Object> map = objectMapper.convertValue(response, Map.class);
            kakaoId = map.get("id").toString();
        } catch (Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E0010");
        }
        return kakaoId;
    }
}
