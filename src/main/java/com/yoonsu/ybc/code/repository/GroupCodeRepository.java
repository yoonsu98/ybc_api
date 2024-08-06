package com.yoonsu.ybc.code.repository;

import com.yoonsu.ybc.code.entity.GroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.yoonsu.ybc.code.repository
 * fileName       : CodeRepository
 * author         : yoons
 * date           : 2023-12-16
 * description    : 공통코드 Repository
 */
@Repository
public interface GroupCodeRepository extends JpaRepository<GroupCode, String> {
    GroupCode findByColumnNm(String columnNm);
}
