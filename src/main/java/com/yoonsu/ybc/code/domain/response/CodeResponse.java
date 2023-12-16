package com.yoonsu.ybc.code.domain.response;

import lombok.*;

/**
 * packageName    : com.yoonsu.ybc.code.domain.response
 * fileName       : CodeResponse
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드 Response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CodeResponse {
    private String cd;                                      /* 코드 */
    private String cdNm;                                    /* 코드명 */
}
