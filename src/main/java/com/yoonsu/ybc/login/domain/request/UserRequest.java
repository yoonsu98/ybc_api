package com.yoonsu.ybc.login.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.yoonsu.ybc.login.domain.request
 * fileName       : UserRequest
 * author         : yoons
 * date           : 2023-12-15
 * description    : 사용자 Request
 */
@Getter
@NoArgsConstructor
public class UserRequest {
    private String accessToken;
    private String refreshToken;
    private String kakaoId;
    private String teamDcd;
    private String nickname;
}
