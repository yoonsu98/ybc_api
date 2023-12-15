package com.yoonsu.ybc.login.service;

import com.yoonsu.ybc.config.exception.ApiException;
import com.yoonsu.ybc.login.domain.response.UserResponse;
import com.yoonsu.ybc.login.entity.User;
import com.yoonsu.ybc.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName    : com.yoonsu.ybc.login.service
 * fileName       : UserService
 * author         : yoons
 * date           : 2023-12-15
 * description    : 사용자 Service
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * userNo로 회원 조회
     * @param userNo
     * @return
     * @throws Exception
     */
    public UserResponse getUserInfo(Long userNo) {
        if(userNo == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E9999");
        }
        Optional<User> findById = userRepository.findById(userNo);
        User user = findById.orElseThrow(() -> {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E1000");
        });
        return UserResponse.of(user);
    }
}
