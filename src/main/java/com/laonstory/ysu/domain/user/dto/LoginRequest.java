package com.laonstory.ysu.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotNull
    @NotBlank
    @Length(min = 10, max = 10)
    private String studentId;

    @NotNull
    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

}
