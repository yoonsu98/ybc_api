package com.yoonsu.ybc.config.advice;

import com.yoonsu.ybc.common.enums.ErrorEnum;
import com.yoonsu.ybc.config.exception.ApiException;
import com.yoonsu.ybc.config.response.ErrorMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * packageName    : com.yoonsu.ybc.config.advice
 * fileName       : ExceptionAdvice
 * author         : yoons
 * date           : 2023-12-15
 * description    : 공통 Exception Handler
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorMap handleException(Exception e) {
        log.error("INTERNAL_SERVER_ERROR : {}", stacktrace(e));
        return new ErrorMap(ErrorEnum.INTERNAL_SERVER_ERROR.getCode());
    }

    @ExceptionHandler(ApiException.class)
    private ResponseEntity<ErrorMap> handleApiException(ApiException e) {
        log.error("ApiException : {}", ErrorEnum.findMessageByCode(e.getCode()));
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorMap(e.getCode()));
    }

    public String stacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}

