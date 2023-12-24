package com.yoonsu.ybc.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final RestApiTemplate restApiTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

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
            restApiTemplate.setHeader(userRequest.getKakaoToken());
            Object response = restApiTemplate.get("https://kapi.kakao.com/v1/user/access_token_info");
            Map<String, Object> map = objectMapper.convertValue(response, Map.class);
            String kakaoId = map.get("id").toString();
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
            // TODO : 자동 로그인
        }
    }
}
