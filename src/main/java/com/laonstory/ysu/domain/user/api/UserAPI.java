package com.laonstory.ysu.domain.user.api;

import com.laonstory.ysu.domain.user.application.UserInfoService;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.TokenResponse;
import com.laonstory.ysu.domain.user.dto.UpdateFcmTokenRequest;
import com.laonstory.ysu.domain.user.dto.UserResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAPI {

    private final UserInfoService userInfoService;

    @GetMapping("/me")
    public ApiResponse<TokenResponse> me(@AuthenticationPrincipal User user) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.me(user));
    }

    @PatchMapping("/fcm")
    public ApiResponse<Boolean> updateFcmToken(@AuthenticationPrincipal User user, @Valid @RequestBody UpdateFcmTokenRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.updateFcmToken(user, dto));
    }

}
