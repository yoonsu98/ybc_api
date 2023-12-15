package com.yoonsu.ybc.login.controller;

import com.yoonsu.ybc.login.domain.response.UserResponse;
import com.yoonsu.ybc.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

