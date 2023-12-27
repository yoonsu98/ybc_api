package com.yoonsu.ybc.api.kakao.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.yoonsu.ybc.common.api.kakao.domain.response
 * fileName       : KakaoResponse
 * author         : yoons
 * date           : 2023-12-16
 * description    : Kakao Response
 */
@Getter
@NoArgsConstructor
public class KakaoResponse {
    private String token_type;                                  /* 토큰 타입, bearer로 고정 */
    private String access_token;                                /* 사용자 액세스 토큰 값 */
    private Integer expires_in;                                 /* 액세스 토큰과 ID 토큰의 만료 시간(초) */
    private String refresh_token;                               /* 사용자 리프레시 토큰 값 */
    private Integer refresh_token_expires_in;                   /* 리프레시 토큰 만료 시간(초) */
}
