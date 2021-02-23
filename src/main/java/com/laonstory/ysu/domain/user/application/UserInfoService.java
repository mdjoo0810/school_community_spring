package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.user.domain.SMSAuth;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.*;
import com.laonstory.ysu.domain.user.exception.SMSNotFoundException;
import com.laonstory.ysu.domain.user.exception.UserNotFoundException;
import com.laonstory.ysu.domain.user.persistance.SMSJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserRepositorySupport;
import com.laonstory.ysu.global.component.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserJpaRepository userJpaRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final SMSJpaRepository smsJpaRepository;
    /**
     * 내 정보 요청 및 토큰 갱신 메서드
     * @param user : token 에 맞는 유저
     * @return TokenResponse
     */
    public TokenResponse me (User user) {
        //
        User result = userJpaRepository.findById(user.getId()).orElseThrow();
        // 토큰 발행
        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
        return new TokenResponse(token, result);
    }

    /**
     * 푸시 메세지를 위한 FCM Token 업데이트 메서드
     * @param user : token 에 맞는 유저
     * @param dto : token 업데이트에 필요한 정보
     * @return UserResponse
     */
    @Transactional
    public Boolean updateFcmToken(User user, UpdateFcmTokenRequest dto) {
        user.updateFcmToken(dto.getFcmToken());
        userJpaRepository.save(user);
        return true;
    }

    /**
     * 계정 찾기 시 해당 정보에 맞는 유저가 있는지 확인 메서드
     * @param dto : 계정 찾기에 필요한 정보
     * @return UserResponse
     */
    public UserResponse checkUser(CheckUserRequest dto) {
        User user = userRepositorySupport.findByStudentIdAndPhone(dto);

        return new UserResponse(user);
    }


    /**
     * 비밀번호 변경 메서드
     * @param dto : 비밀번호 변경 시 필요한 정보
     * @return UserResponse
     */
    @Transactional
    public UserResponse changePassword(ChangePasswordRequest dto) {

        // 문자인증을 성공했는가?
        Optional<SMSAuth> smsAuth = smsJpaRepository.findByPhoneAndCheckedTrue(dto.getPhone());

        if(smsAuth.isEmpty()) throw new SMSNotFoundException(dto.getPhone());

        smsJpaRepository.delete(smsAuth.get());

        User user = userRepositorySupport.findByPhone(dto.getPhone());

        // 비밀번호 변경
        user.changePassword(passwordEncoder.encode(dto.getPassword()));

        return new UserResponse(user);
    }

}
