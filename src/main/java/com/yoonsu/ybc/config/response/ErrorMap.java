package com.yoonsu.ybc.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMap {
    private String message;
    private String code;
}