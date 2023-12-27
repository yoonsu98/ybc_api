package com.yoonsu.ybc.login.entity;

import com.yoonsu.ybc.login.domain.request.UserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * packageName    : com.yoonsu.ybc.login.entity
 * fileName       : User
 * author         : yoons
 * date           : 2023-12-15
 * description    : 사용자 Entity
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name="tb_user", schema = "ybc")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;                                /* 사용자 번호 */
    private String kakaoToken;                          /* 카카오 토큰 */
    private String kakaoId;                             /* 카카오 아이디 */
    private String nickname;                            /* 닉네임 */
    private String teamDcd;                             /* [100] 팀 구분코드 */
    private String wthdrYn;                             /* 탈퇴 여부 */
    private LocalDateTime joinDate;                     /* 가입 일시 */
    private LocalDateTime wthdrDate;                    /* 탈퇴 일시 */
    private LocalDateTime regDate;                      /* 등록일시 */
    private String regId;                               /* 등록자 */
    private LocalDateTime updateDate;                   /* 수정일시 */
    private String updateId;                            /* 수정 */

    /* 회원가입 */
    public void save(UserRequest userRequest) {
        this.kakaoToken = userRequest.getKakaoRefreshToken();
        this.kakaoId = userRequest.getKakaoId();
        this.nickname = userRequest.getNickname();
        this.teamDcd = userRequest.getTeamDcd();
        this.joinDate = LocalDateTime.now();
        this.regDate = LocalDateTime.now();
    }
}