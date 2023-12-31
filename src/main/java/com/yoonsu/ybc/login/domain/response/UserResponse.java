package com.yoonsu.ybc.login.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yoonsu.ybc.api.kakao.domain.response.KakaoResponse;
import com.yoonsu.ybc.login.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * packageName    : com.yoonsu.ybc.login.domain.response
 * fileName       : UserResponse
 * author         : yoons
 * date           : 2023-12-16
 * description    : 사용자 Response
 */
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long userNo;                                /* 사용자 번호 */
    private String kakaoId;                             /* 카카오 아이디 */
    private String nickname;                            /* 닉네임 */
    private String teamDcd;                             /* [100] 팀 구분코드 */
    private String wthdrYn;                             /* 탈퇴 여부 */
    private LocalDateTime joinDate;                     /* 가입 일시 */
    private LocalDateTime wthdrDate;                    /* 탈퇴 일시 */
    private String accessToken;                         /* ybc access_token */
    private String refreshToken;                        /* ybc refresh_token */

    public static UserResponse of(User user) {
        UserResponse userResponse = UserResponse.builder()
                .refreshToken(user.getRefreshToken())
                .userNo(user.getUserNo())
                .nickname(user.getNickname())
                .teamDcd(user.getTeamDcd())
                .joinDate(user.getJoinDate())
                .wthdrDate(user.getWthdrDate())
                .wthdrYn(user.getWthdrYn())
                .build();
        return userResponse;
    }
}
