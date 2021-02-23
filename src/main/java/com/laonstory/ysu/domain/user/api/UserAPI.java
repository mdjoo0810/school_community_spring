package com.laonstory.ysu.domain.user.api;

import com.laonstory.ysu.domain.user.application.UserInfoService;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.domain.user.dto.*;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/major")
    public ApiResponse<UserResponse> changeUserMajor(@AuthenticationPrincipal User user, @Valid @RequestBody ChangeMajorRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.changeMajor(user, dto));
    }

    @PutMapping("/profile")
    public ApiResponse<UserResponse> changeProfile(@AuthenticationPrincipal User user, @RequestParam MultipartFile profile) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.changeProfile(user, profile));
    }

    @DeleteMapping("/profile")
    public ApiResponse<UserResponse> deleteProfile(@AuthenticationPrincipal User user) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.deleteProfile(user));
    }

    @PatchMapping("/password")
    public ApiResponse<UserResponse> changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody ChangePasswordByCurrentPasswordRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.changePasswordByCurrentPassword(user, dto));
    }

    @DeleteMapping()
    public ApiResponse<Boolean> withdraw(@AuthenticationPrincipal User user) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.withdraw(user));
    }

}
