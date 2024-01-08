package com.yoonsu.ybc.config;

import com.yoonsu.ybc.common.utils.JwtProvider;
import com.yoonsu.ybc.config.exception.ApiException;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        log.debug(request.getRequestURI());
        log.debug(request.getRequestURL().toString());

        Cookie[] getCookie = request.getCookies();

        String token = null;

        if(getCookie != null){
            for(int i=0; i<getCookie.length; i++){
                Cookie cookie = getCookie[i];

                if("access_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        } else {
            token = request.getHeader("access_token");
        }

        //유효토큰 확인
        if(!jwtProvider.validateToken(token)) {
            log.error("유효하지 않은 토큰");
//            throw new ApiException(HttpStatus.FORBIDDEN, "E0002");
        }

        // TODO : access_token이 유효X, refresh_token 유효 ->  access_token 발급
        //  access_token이 유효X, refresh_token 유효X ->  throw new exception (403)
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
