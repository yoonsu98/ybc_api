package com.yoonsu.ybc.config.response;

import com.yoonsu.ybc.common.enums.ErrorEnum;
import lombok.*;

/**
 * packageName    : com.yoonsu.ybc.config.response
 * fileName       : ErrorMap
 * author         : yoons
 * date           : 2023-12-15
 * description    : Error 발생 시 return하는 Map
 */
@Setter
@Getter
@ToString
public class ErrorMap {
    private String code;
    private String message;

    public ErrorMap(String code) {
        this.code = code;
        this.message = ErrorEnum.findMessageByCode(code);
    }
}