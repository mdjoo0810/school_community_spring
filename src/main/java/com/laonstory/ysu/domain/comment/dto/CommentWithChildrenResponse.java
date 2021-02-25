package com.laonstory.ysu.domain.comment.dto;

import com.laonstory.ysu.domain.comment.domain.Comment;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CommentWithChildrenResponse extends CommentResponse {

    private List<CommentResponse> children;
    public CommentWithChildrenResponse(Comment comment, User user) {
        super(comment, user);
        this.children = comment.getChildren().stream().map(e -> new CommentResponse(e, user)).collect(Collectors.toList());
    }

}
