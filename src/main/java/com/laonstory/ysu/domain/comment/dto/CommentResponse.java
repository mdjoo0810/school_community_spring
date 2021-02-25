package com.laonstory.ysu.domain.comment.dto;

import com.laonstory.ysu.domain.comment.domain.Comment;
import com.laonstory.ysu.domain.organization.domain.OgzNoticeComment;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private String writer;
    private String content;
    private int likes;
    private Boolean hasLike;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentResponse (Comment comment, User user) {
        this.id = comment.getId();
        this.writer = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.hasLike = comment.getLikes().stream().anyMatch(e -> e.getUser().getId().equals(user.getId()));
        this.likes = comment.getLikes().size();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
