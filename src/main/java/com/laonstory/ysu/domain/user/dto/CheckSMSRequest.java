package com.laonstory.ysu.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckSMSRequest {

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
    @Length(min = 6, max = 6)
    private String code;

}
