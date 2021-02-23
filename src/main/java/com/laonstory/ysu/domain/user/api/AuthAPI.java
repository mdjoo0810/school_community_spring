package com.laonstory.ysu.domain.user.api;

import com.laonstory.ysu.domain.user.application.RegisterService;
import com.laonstory.ysu.domain.user.dto.ExistStudentIdRequest;
import com.laonstory.ysu.domain.user.dto.RegisterRequest;
import com.laonstory.ysu.domain.user.dto.TokenResponse;
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

    // TODO: 로그인
    // TODO: 회원가입
    // TODO: 학번 인증
    // TODO: 계정 찾기
    // TODO: 인증 번호 요청
    // TODO: 비밀번호 변경

    private final RegisterService registerService;

    @PostMapping("/login")
    public ApiResponse<String> login() {
        return new ApiResponse<>(HttpStatus.OK, "success");
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
     * @param dto : 학번 인증에 필요한 정보
     * @return Boolean
     */
    @PostMapping("/student/id/exist")
    public ApiResponse<Boolean> existStudentId(@Valid @RequestBody ExistStudentIdRequest dto) {

        log.info("exist check student ID ---> {}, {}", dto.getName(), dto.getStudentId());

        return new ApiResponse<>(HttpStatus.OK, true);
    }



}
