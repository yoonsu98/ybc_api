package com.yoonsu.ybc.api.kakao.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.yoonsu.ybc.common.api.kakao.domain.request
 * fileName       : KakaoRequest
 * author         : yoons
 * date           : 2023-12-16
 * description    : Kakao Request
 */
@Getter
@NoArgsConstructor
public class KakaoRequest {
    private String grant_type = "authorization_code";       /* authorization_code로 고정 */
    private String client_id;                               /* 앱 REST API 키 */
    private String redirect_url;                            /* 인가 코드가 리다이렉트된 URI */
    private String code;                                    /* 인가 코드 받기 요청으로 얻은 인가 코드 */
    private String client_secret;                           /* 토큰 발급 시, 보안을 강화하기 위해 추가 확인하는 코드 */
}
