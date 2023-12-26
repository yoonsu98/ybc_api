package com.yoonsu.ybc;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.yoonsu.ybc
 * fileName       : PageController
 * author         : yoons
 * date           : 2023-12-18
 * description    : 화면 Controller
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class PageController {

     //메인화면
    @RequestMapping(value = "/main")
    public String main(HttpServletRequest request, HttpSession session, Model model) {
        log.debug("login");
        return "main";
    }

     //회원가입화면
    @RequestMapping(value = "/kakao")
    public String kakao(HttpServletRequest request, HttpSession session, Model model) {
        log.debug("kakao");
        return "kakao";
    }
}
