package com.yoonsu.ybc.code.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.yoonsu.ybc.code.entity
 * fileName       : Code
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드
 */
@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CodePK.class)
@Table(name = "tb_code", schema = "ybc")
public class Code {
    @Id
    private String groupCd;                                 /* 그룹코드 */
    @Id
    private String cd;                                      /* 코드 */
    private String cdNm;                                    /* 코드명 */
    private String comment;                                 /* 설명 */
    private String usg1;                                    /* 용도1 */
    private String usg2;                                    /* 용도2 */
    private String usg3;                                    /* 용도3 */
    private String description;                             /* 용도 설명 */
    private String useYn;                                   /* 사용여부 */
    private LocalDateTime regDate;                          /* 등록일시 */
    private String regId;                                   /* 등록자 */
    private LocalDateTime updateDate;                       /* 수정일시 */
    private String updateId;                                /* 수정자 */
}
