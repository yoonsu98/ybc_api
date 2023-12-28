package com.yoonsu.ybc.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonsu.ybc.api.kakao.service.KakaoService;
import com.yoonsu.ybc.common.utils.RestApiTemplate;
import com.yoonsu.ybc.config.exception.ApiException;
import com.yoonsu.ybc.login.domain.request.UserRequest;
import com.yoonsu.ybc.login.domain.response.UserResponse;
import com.yoonsu.ybc.login.entity.User;
import com.yoonsu.ybc.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
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
    private final KakaoService kakaoService;

    /**
     * userNo로 회원 조회
     *
     * @param userNo
     * @return
     * @throws Exception
     */
    public UserResponse getUserInfo(Long userNo) {
        if (userNo == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E9999");
        }
        Optional<User> findById = userRepository.findById(userNo);
        User user = findById.orElseThrow(() -> {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E1000");
        });
        return UserResponse.of(user);
    }

    /**
     * 회원 정보 저장
     *
     * @param userRequest
     */
    public void registryUserInfo(UserRequest userRequest) {
        User user = new User();
        user.save(userRequest);
        userRepository.save(user);
    }

    /**
     * 카카오 토큰으로 회원 정보 조회
     * @param userRequest
     * @return
     */
    public UserResponse getTokenInfo(UserRequest userRequest) {
        try {
            String kakaoId = kakaoService.getTokenInfo(userRequest);
            this.findByKakaoId(kakaoId);
            return UserResponse.builder().kakaoId(kakaoId).build();
        } catch (Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E0010");
        }
    }

    /**
     * 카카오 아이디로 회원 조회
     * @param kakaoId
     */
    public void findByKakaoId(String kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId);
        if(user != null) {
            // TODO : 내 서비스의 access_token, refresh_token 만들어서 세팅. 카카오 토큰은 검증용으로만 사용
        }
    }
}
