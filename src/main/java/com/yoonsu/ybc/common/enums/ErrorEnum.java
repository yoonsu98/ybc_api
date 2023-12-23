package com.yoonsu.ybc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.yoonsu.ybc.common.enums
 * fileName       : ErrorEnum
 * author         : yoons
 * date           : 2023-12-15
 * description    : Error Enum
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    INTERNAL_SERVER_ERROR("E0001", "서버 에러가 발생했습니다."),
    KAKAO_RESPONSE_ERROR("E0010", "통신 에러가 발생했습니다."),
    USER_NOT_EXISTS("E1000", "해당 회원이 존재하지 않습니다."),
    COLUMN_NOT_EXISTS("E9998", "해당 컬럼명이 존재하지 않습니다."),
    PARAM_REQUIRE("E9999", "필수 파라미터가 필요합니다."),
    ;
    private String code;
    private String message;

    /**
     * Code값으로 Message 찾기
     * @param code
     * @return
     */
    public static String findMessageByCode(String code) {
        ErrorEnum[] values = ErrorEnum.values();
        for (ErrorEnum value : values) {
            if(code.equals(value.getCode())){
                return value.getMessage();
            }
        }
        return null;
    }
}
