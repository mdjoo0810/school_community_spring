package com.laonstory.ysu.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class RegisterRequest {

    @NotNull
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 10, max = 10)
    private String studentId;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    private Long majorId;

    private Long subMajorId;

}
