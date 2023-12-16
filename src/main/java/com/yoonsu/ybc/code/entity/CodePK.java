package com.yoonsu.ybc.code.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * packageName    : com.yoonsu.ybc.code.entity
 * fileName       : CodePK
 * author         : yoons
 * date           : 2023-12-16
 * description    : tb_code PK
 */
@Embeddable
public class CodePK implements Serializable {
    private String groupCd;                                 /* 그룹코드 */
    private String cd;                                      /* 코드 */
}
