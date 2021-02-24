package com.laonstory.ysu.domain.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CommentRequest {

    private Long parent;

    @NotNull
    @NotBlank
    private String content;

}
