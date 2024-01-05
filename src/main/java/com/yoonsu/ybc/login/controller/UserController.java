package com.yoonsu.ybc.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yoonsu.ybc.api.kakao.domain.request.KakaoRequest;
import com.yoonsu.ybc.login.domain.request.UserRequest;
import com.yoonsu.ybc.login.domain.response.UserResponse;
import com.yoonsu.ybc.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.yoonsu.ybc.login.controller
 * fileName       : UserController
 * author         : yoons
 * date           : 2023-12-15
 * description    : 사용자 Controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/getUserInfo")
    public UserResponse getUserInfo(@RequestParam(value = "userNo", required = false) Long userNo) {
        return userService.getUserInfo(userNo);
    }
    @PostMapping(value = "/registryUserInfo")
    public void registryUserInfo(@RequestBody UserRequest userRequest) {
        userService.registryUserInfo(userRequest);
    }

    @PostMapping(value = "/getTokenInfo")
    public UserResponse getTokenInfo(@RequestBody KakaoRequest kakaoRequest) {
        return userService.getTokenInfo(kakaoRequest.getCode());
    }

    // TODO : 로그아웃
}

