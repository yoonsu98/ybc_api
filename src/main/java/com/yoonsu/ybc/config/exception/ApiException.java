package com.yoonsu.ybc.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.yoonsu.ybc.config.exception
 * fileName       : ApiException
 * author         : yoons
 * date           : 2023-12-15
 * description    : Custom Exception
 */
@Getter
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String code;

    public ApiException(HttpStatus httpStatus, String code) {
        this.httpStatus = httpStatus;
        this.code = code;
    }
}

