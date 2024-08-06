package com.yoonsu.ybc.code.controller;

import com.yoonsu.ybc.code.domain.response.CodeResponse;
import com.yoonsu.ybc.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.yoonsu.ybc.code.controller
 * fileName       : CodeController
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드 Controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class CodeController {
    private final CodeService codeService;

    @GetMapping("/findCodeByColumnNm")
    public List<CodeResponse> findCodeByColumnNm(@RequestParam(value = "columnNm") String columnNm) {
        return codeService.findCodeByColumnNm(columnNm);
    }
}
