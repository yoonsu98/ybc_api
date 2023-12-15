package com.yoonsu.ybc.config.advice;

import com.yoonsu.ybc.config.response.BaseResponseUtils;
import com.yoonsu.ybc.config.response.ErrorMap;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * packageName    : com.yoonsu.ybc.config.advice
 * fileName       : ResponseAdvice
 * author         : yoons
 * date           : 2023-12-16
 * description    : Http Response를 BaseResponse로 return
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * beforeBodyWrite 사용 유무
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * body를 공통화된 BaseResponse로 변환
     * @param body the body to be written
     * @param returnType the return type of the controller method
     * @param selectedContentType the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request the current request
     * @param response the current response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return body instanceof ErrorMap ? BaseResponseUtils.error((ErrorMap) body) : BaseResponseUtils.success(body);
    }
}
