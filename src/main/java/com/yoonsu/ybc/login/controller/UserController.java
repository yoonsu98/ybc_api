package com.yoonsu.ybc.login.controller;

import com.yoonsu.ybc.api.kakao.domain.request.KakaoRequest;
import com.yoonsu.ybc.login.domain.request.UserRequest;
import com.yoonsu.ybc.login.domain.response.UserResponse;
import com.yoonsu.ybc.login.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // TODO : Spring security 적용 필요
        Cookie access_cookie = new Cookie("access_token", null);
        access_cookie.setMaxAge(0);
        response.addCookie(access_cookie);

        Cookie refresh_cookie = new Cookie("refresh_token", null);
        refresh_cookie.setMaxAge(0);
        response.addCookie(refresh_cookie);
    }
}

