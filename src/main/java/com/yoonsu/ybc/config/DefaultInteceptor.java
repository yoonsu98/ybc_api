package com.yoonsu.ybc.config;

import com.yoonsu.ybc.common.utils.JwtProvider;
import com.yoonsu.ybc.config.exception.ApiException;
import com.yoonsu.ybc.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.yoonsu.ybc.config
 * fileName       : DefaultInteceptor
 * author         : yoons
 * date           : 2024-01-08
 * description    : 인터셉터
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultInteceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        log.debug(request.getRequestURI());
        log.debug(request.getRequestURL().toString());

        Cookie[] getCookie = request.getCookies();

        String access_token = null;
        String refresh_token = null;

        if (getCookie != null) {
            for (int i = 0; i < getCookie.length; i++) {
                Cookie cookie = getCookie[i];

                if ("access_token".equals(cookie.getName())) {
                    access_token = cookie.getValue();
                }
                if ("refresh_token".equals(cookie.getName())) {
                    refresh_token = cookie.getValue();
                }
            }
        }

        if (access_token == null || refresh_token == null) {
            throw new ApiException(HttpStatus.FORBIDDEN, "E0002");
        }

        //유효토큰 확인
        if (!jwtProvider.validateToken(access_token) && jwtProvider.validateToken(refresh_token)) {
            String kakaoId = userRepository.findByRefreshToken(refresh_token).getKakaoId();
            for (int i = 0; i < getCookie.length; i++) {
                Cookie cookie = getCookie[i];
                if ("access_token".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    break;
                }
            }
            response.addCookie(new Cookie("access_token", jwtProvider.createAccessToken(kakaoId)));
        } else if (!jwtProvider.validateToken(access_token) && !jwtProvider.validateToken(refresh_token)) {
            throw new ApiException(HttpStatus.FORBIDDEN, "E0002");
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 후처리 작업
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // API 호출 완료
    }
}
