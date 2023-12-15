package com.yoonsu.ybc.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long userNo;                                /* 사용자 번호 */
    private String kakaoToken;                          /* 카카오 토큰 */
    private String teamDcd;                             /* [100] 팀 구분코드 */
    private String wthdrYn;                             /* 탈퇴 여부 */
    private LocalDateTime joinDate;                     /* 가입 일시 */
    private LocalDateTime wthdrDate;                    /* 탈퇴 일시 */
    private LocalDateTime regDate;                      /* 등록일시 */
    private String regId;                               /* 등록자 */
    private LocalDateTime updateDate;                   /* 수정일시 */
    private String updateId;                            /* 수정 */
}