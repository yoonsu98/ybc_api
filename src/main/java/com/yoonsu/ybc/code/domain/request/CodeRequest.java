package com.yoonsu.ybc.code.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.yoonsu.ybc.code.domain.request
 * fileName       : CodeRequest
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드 Request
 */
@Getter
@NoArgsConstructor
public class CodeRequest {
    private String groupCd;                                 /* 그룹코드 */
    private String cd;                                      /* 코드 */
}
