package com.yoonsu.ybc.code.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.yoonsu.ybc.code.entity
 * fileName       : GroupCode
 * author         : yoons
 * date           : 2023-12-16
 * description    : 그룹코드 테이블
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_group_code", schema = "ybc")
public class GroupCode {
    @Id
    private String groupCd;                                 /* 그룹코드 */
    private String groupCdNm;                               /* 그룹코드명 */
    private String columnNm;                                /* 컬럼명 */
    private LocalDateTime regDate;                          /* 등록일시 */
    private String regId;                                   /* 등록자 */
    private LocalDateTime updateDate;                       /* 수정일시 */
    private LocalDateTime updateId;                         /* 수정자 */
}
