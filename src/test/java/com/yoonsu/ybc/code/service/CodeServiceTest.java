package com.yoonsu.ybc.code.service;

import com.yoonsu.ybc.code.domain.response.CodeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.yoonsu.ybc.code.service
 * fileName       : CodeServiceTest
 * author         : yoons
 * date           : 2023-12-18
 * description    :
 */
@SpringBootTest
class CodeServiceTest {
    @Autowired
    private CodeService codeService;

    @Test
    public void findCodeByColumnNm() {
        List<CodeResponse> codeList = codeService.findCodeByColumnNm("teamDcd");
        for(CodeResponse codeResponse : codeList){
            System.out.println("codeResponse.toString() = " + codeResponse.toString());
        }
    }

    @Test
    public void findCodeByGroupCd() {
        List<CodeResponse> codeList = codeService.findCodeByGroupCd("100");
        for(CodeResponse codeResponse : codeList){
            System.out.println("codeResponse.toString() = " + codeResponse.toString());
        }
    }
}