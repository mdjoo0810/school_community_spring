package com.laonstory.ysu.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateFcmTokenRequest {

    @NotNull
    @NotBlank
    private String fcmToken;

}
