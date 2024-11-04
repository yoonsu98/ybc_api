package com.yoonsu.ybc.code.domain.response;

import com.yoonsu.ybc.code.entity.Code;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.yoonsu.ybc.code.domain.response
 * fileName       : CodeResponse
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드 Response
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CodeResponse {
    private String cd;                                      /* 코드 */
    private String cdNm;                                    /* 코드명 */
    private String comment;                                 /* 설명 */
    private String usg1;                                    /* 용도1 */
    private String usg2;                                    /* 용도2 */
    private String usg3;                                    /* 용도3 */
    private String description;                             /* 용도 설명 */
    public static List<CodeResponse> of(List<Code> codeList) {
        List<CodeResponse> response = new ArrayList<>();
        for(Code code : codeList) {
            CodeResponse codeResponse = CodeResponse.builder()
                    .cd(code.getCd())
                    .cdNm(code.getCdNm())
                    .comment(code.getComment())
                    .usg1(code.getUsg1())
                    .usg2(code.getUsg2())
                    .usg3(code.getUsg3())
                    .description(code.getDescription())
                    .build();
            response.add(codeResponse);
        }
        return response;
    }
}
