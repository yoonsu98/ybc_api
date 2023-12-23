package com.yoonsu.ybc.common.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestApiTemplate {

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @PostConstruct
    private void initialize() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
    }

    public void setHeader(MediaType mediaType, String token) {
        this.headers.remove("Authorization");
        this.headers.setContentType(mediaType);
        if (token != null) this.headers.add("Authorization", token);
    }

    public void setHeader(String key, String value) {
        this.headers.remove(key);
        if (value != null) this.headers.add(key, value);
    }

    public Object get(String url) throws Exception {
        return getResponseData(url, false);
    }

    public Object get(String url, Boolean isJSonArray) throws Exception {
        return getResponseData(url, isJSonArray);
    }

    private Object getResponseData(String url, Boolean isJSonArray) throws Exception {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                isJSonArray ? ParameterizedTypeReference.forType(ArrayList.class) : ParameterizedTypeReference.forType(Map.class)
        );
        if (responseEntity.getStatusCode() != HttpStatus.OK) throw new Exception("통신 실패");
        return responseEntity.getBody();
    }

    public Object post(String url) throws Exception {
        return this.postResponseData(url, null, false);
    }

    public Object post(String url, Object body) throws Exception {
        return this.postResponseData(url, body, false);
    }

    public Object post(String url, Object body, Boolean isJSonArray) throws Exception {
        return postResponseData(url, body, isJSonArray);
    }

    private Object postResponseData(String url, Object body, Boolean isJSonArray) throws Exception {
        HttpEntity<Object> requestEntity = new HttpEntity(body, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                isJSonArray ? ParameterizedTypeReference.forType(ArrayList.class) : ParameterizedTypeReference.forType(Map.class)
        );
        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new Exception("" + responseEntity.getStatusCodeValue() + responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public Object put(String url) throws Exception {
        return this.put(url, null, false);
    }

    public Object put(String url, Object body) throws Exception {
        return this.putResponseData(url, body, false);
    }

    public Object put(String url, Object body, Boolean isJSonArray) throws Exception {
        return putResponseData(url, body, isJSonArray);
    }

    private Object putResponseData(String url, Object body, Boolean isJSonArray) throws Exception {
        HttpEntity<Object> requestEntity = new HttpEntity(body, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                isJSonArray ? ParameterizedTypeReference.forType(ArrayList.class) : ParameterizedTypeReference.forType(Map.class)
        );
        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new Exception("" + responseEntity.getStatusCodeValue() + responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public Object delete(String url) throws Exception {
        return deleteResponseData(url, false);
    }

    public Object delete(String url, Boolean isJSonArray) throws Exception {
        return deleteResponseData(url, isJSonArray);
    }

    private Object deleteResponseData(String url, Boolean isJSonArray) throws Exception {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                isJSonArray ? ParameterizedTypeReference.forType(ArrayList.class) : ParameterizedTypeReference.forType(Map.class)
        );
        if (responseEntity.getStatusCode() != HttpStatus.OK) throw new Exception("통신 실패");
        return responseEntity.getBody();
    }
}
