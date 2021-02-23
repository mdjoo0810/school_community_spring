package com.laonstory.ysu.domain.user.application;

import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.TokenResponse;
import com.laonstory.ysu.domain.user.dto.UpdateFcmTokenRequest;
import com.laonstory.ysu.domain.user.dto.UserResponse;
import com.laonstory.ysu.domain.user.exception.UserNotFoundException;
import com.laonstory.ysu.domain.user.persistance.UserJpaRepository;
import com.laonstory.ysu.domain.user.persistance.UserRepositorySupport;
import com.laonstory.ysu.global.component.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserJpaRepository userJpaRepository;
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

}
