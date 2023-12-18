package com.yoonsu.ybc.code.service;

import com.yoonsu.ybc.code.domain.response.CodeResponse;
import com.yoonsu.ybc.code.entity.Code;
import com.yoonsu.ybc.code.entity.GroupCode;
import com.yoonsu.ybc.code.repository.CodeRepository;
import com.yoonsu.ybc.code.repository.GroupCodeRepository;
import com.yoonsu.ybc.common.enums.ErrorEnum;
import com.yoonsu.ybc.config.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.yoonsu.ybc.code.service
 * fileName       : CodeService
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드 Service
 */
@RequiredArgsConstructor
@Service
public class CodeService {
    private final CodeRepository codeRepository;
    private final GroupCodeRepository groupCodeRepository;

    /**
     * 컬럼명으로 공통코드 리스트 찾기
     * @param columnNm
     * @return
     */
    public List<CodeResponse> findCodeByColumnNm(String columnNm) {
        GroupCode byColumnNm = groupCodeRepository.findByColumnNm(columnNm);
        if(byColumnNm == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "E9998");
        }
        List<Code> codeList = codeRepository.findCodeByGroupCdAndUseYn(byColumnNm.getGroupCd(), "Y");
        return CodeResponse.of(codeList);
    }

    /**
     * groupCd로 공통코드 리스트 찾기
     * @param groupCd
     * @return
     */
    public List<CodeResponse> findCodeByGroupCd(String groupCd) {
        List<Code> codeList = codeRepository.findCodeByGroupCdAndUseYn(groupCd, "Y");
        return CodeResponse.of(codeList);
    }
}
