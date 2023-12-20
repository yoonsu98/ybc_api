package com.yoonsu.ybc.login.repository;

import com.yoonsu.ybc.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.yoonsu.ybc.login.repository
 * fileName       : UserRepository
 * author         : yoons
 * date           : 2023-12-15
 * description    : 사용자 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // TODO : 카카오 토큰으로 신규 유저 생성
}
