package com.yoonsu.ybc.login.service;

import com.yoonsu.ybc.login.domain.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * packageName    : com.yoonsu.ybc.login.service
 * fileName       : UserServiceTest
 * author         : yoons
 * date           : 2023-12-16
 * description    :
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void main() {
        UserResponse userInfo = userService.getUserInfo(2L);
        System.out.println("userInfo.getUserNo() = " + userInfo.getUserNo());
    }

}