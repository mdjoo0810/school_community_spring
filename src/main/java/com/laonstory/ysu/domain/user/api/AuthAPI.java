package com.laonstory.ysu.domain.user.api;

import com.laonstory.ysu.domain.user.application.SMSService;
import com.laonstory.ysu.domain.user.application.LoginService;
import com.laonstory.ysu.domain.user.application.RegisterService;
import com.laonstory.ysu.domain.user.application.UserInfoService;
import com.laonstory.ysu.domain.user.dto.*;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthAPI {

    // TODO: 계정 찾기
    // TODO: 비밀번호 변경

    private final RegisterService registerService;
    private final LoginService loginService;
    private final UserInfoService userInfoService;
    private final SMSService smsService;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@Valid @RequestBody LoginRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, loginService.login(dto));
    }

    /**
     * 회원가입 API
     * @param dto : 회원가입에 필요한 정보
     * @return TokenResponse
     */
    @PostMapping("/register")
    public ApiResponse<TokenResponse> register(@Valid @RequestBody RegisterRequest dto) {
        return new ApiResponse<>(HttpStatus.CREATED, registerService.register(dto));
    }

    /**
     * 학번 인증 API
     * TODO: 실제 학번으로 변경
     * @param dto : 학번 인증에 필요한 정보
     * @return Boolean
     */
    @PostMapping("/student/id/exist")
    public ApiResponse<Boolean> existStudentId(@Valid @RequestBody ExistStudentIdRequest dto) {

        log.info("exist check student ID ---> {}, {}", dto.getName(), dto.getStudentId());

        return new ApiResponse<>(HttpStatus.OK, true);
    }

    /**
     * 계정 찾기 시 해당 유저가 있는지 확인
     * @param dto : 계정 찾기에 필요한 정보
     * @return UserResponse
     */
    @PostMapping("/check")
    public ApiResponse<UserResponse> checkUser(@Valid @RequestBody CheckUserRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.checkUser(dto));
    }

    @PostMapping("/password")
    public ApiResponse<UserResponse> changePassword(@Valid @RequestBody ChangePasswordRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, userInfoService.changePassword(dto));
    }

    /**
     * 문자 인증 요청 - 전송
     * @param dto : 문자 인증에 필요한 정보
     * @return Boolean
     */
    @PostMapping("/sms")
    public ApiResponse<Boolean> requestSMSAuth(@Valid @RequestBody SendSMSRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, smsService.sendSMS(dto));
    }

    /**
     * 문자 인증 요청 - 확인
     * @param dto : 문자 인증에 필요한 정보
     * @return Boolean
     */
    @PostMapping("/sms/check")
    public ApiResponse<Boolean> checkSMSAuth(@Valid @RequestBody CheckSMSRequest dto) {
        return new ApiResponse<>(HttpStatus.OK, smsService.checkSMSAuth(dto));
    }



}
