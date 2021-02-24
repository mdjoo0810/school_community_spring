package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.LoginRequest;
import com.laonstory.ysu.domain.user.dto.TokenResponse;
import com.laonstory.ysu.domain.user.exception.*;
import com.laonstory.ysu.domain.user.persistence.UserRepositorySupport;
import com.laonstory.ysu.global.component.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserRepositorySupport userRepositorySupport;

    /**
     * 로그인 메서드
     * @param dto : 로그인에 필요한 정보
     * @return TokenResponse
     */
    public TokenResponse login (LoginRequest dto) {
        // 탈퇴 확인
        isWithdrawUser(dto.getStudentId());

        // 유저 있는지 확인
        User user = userRepositorySupport.findByStudentId(dto.getStudentId());

        // 비밀번호 확인
        matchPassword(user.getPassword(), dto.getPassword());

        // lastLoginDate Update
        user.login();

        // 토큰 발행
        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());

        return new TokenResponse(token, user);
    }

    /**
     * 비밀번호가 맞는 지 확인
     * @param encodedPassword : User 정보에 저장된 암호화된 비밀번호
     * @param password 유저가 입력한 비밀번호
     */
    private void matchPassword(String encodedPassword, String password) {
        if (!passwordEncoder.matches(password, encodedPassword)) throw new PasswordNotMatchedException(password);
    }

    /**
     * 탈퇴된 회원인지 확인
     * @param studentId : 학번
     */
    private void isWithdrawUser(String studentId) {
        Boolean withdraw = userRepositorySupport.isWithdrawUser(studentId);
        if (withdraw) throw new AlreadyWithdrawUserException(studentId);
    }
}
