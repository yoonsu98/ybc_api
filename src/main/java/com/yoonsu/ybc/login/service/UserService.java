package com.yoonsu.ybc.login.service;

import com.yoonsu.ybc.api.kakao.domain.response.KakaoResponse;
import com.yoonsu.ybc.api.kakao.service.KakaoService;
import com.yoonsu.ybc.common.utils.JwtProvider;
import com.yoonsu.ybc.config.exception.ApiException;
import com.yoonsu.ybc.login.domain.request.UserRequest;
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
    private final KakaoService kakaoService;
    private final JwtProvider jwtProvider;

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
        user.save(userRequest, jwtProvider.createRefreshToken());
        userRepository.save(user);
    }

    /**
     * 인가코드로 token 정보 조회
     *
     * @param code
     * @return
     */
    public UserResponse getTokenInfo(String code) {
        UserResponse response = null;
        KakaoResponse kakaoResponse = kakaoService.getToken(code);
        if (kakaoResponse != null) {
            String kakaoId = kakaoService.getTokenInfo(kakaoResponse.getAccess_token());
            User user = userRepository.findByKakaoId(kakaoId);
            // 회원인 경우 token 만들고 바로 로그인
            if (user != null) {
                response = this.login(kakaoId);
            }
            else {
                response = UserResponse.builder().kakaoId(kakaoId).build();
            }
        }
        return response;
    }

    /**
     * token 발급(로그인)
     * @param kakaoId
     * @return
     */
    public UserResponse login(String kakaoId) {
        String accessToken = jwtProvider.createAccessToken(kakaoId);
        String refreshToken = jwtProvider.createRefreshToken();

        User user = userRepository.findByKakaoId(kakaoId);
        user.save(refreshToken);
        userRepository.save(user);

        return UserResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .nickname(user.getNickname())
                .build();
    }
}
