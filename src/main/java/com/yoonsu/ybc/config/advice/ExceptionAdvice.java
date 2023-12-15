package com.yoonsu.ybc.config.advice;

import com.yoonsu.ybc.config.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Object> handleException(Exception e) {
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("message", "서버 에러가 발생 했습니다.");
        log.error("Occurred Exception : {}", stacktrace(e));
        return errorData;
    }

    @ExceptionHandler(ApiException.class)
    private ResponseEntity<Map<String, Object>> handleApiException(ApiException e) {
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("code", e.getCode());
        log.info("ApiException : {}", e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(errorData);
    }

    public String stacktrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}

