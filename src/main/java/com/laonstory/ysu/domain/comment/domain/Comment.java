package com.laonstory.ysu.domain.comment.domain;

import com.laonstory.ysu.domain.comment.dto.CommentRequest;
import com.laonstory.ysu.domain.common.BaseTimeEntity;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_COMMENT")
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parent;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static Comment create(CommentRequest dto, User user) {
        return Comment.builder()
                .parent(dto.getParent() != null ? dto.getParent() : null)
                .content(dto.getContent())
                .user(user)
                .build();
    }

}
