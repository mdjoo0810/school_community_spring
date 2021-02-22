package com.laonstory.ysu.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RegisterRequest {

    @NotNull
    private String name;

    @NotNull
    private String studentId;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long majorId;

    private Long subMajorId;

}
