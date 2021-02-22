package com.laonstory.ysu.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ExistStudentIdRequest {

    @NotNull
    private String name;

    @NotNull
    private String studentId;

}
