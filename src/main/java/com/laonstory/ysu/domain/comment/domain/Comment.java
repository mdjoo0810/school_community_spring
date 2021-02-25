package com.laonstory.ysu.domain.comment.domain;

import com.laonstory.ysu.domain.comment.dto.CommentRequest;
import com.laonstory.ysu.domain.comment.dto.CommentResponse;
import com.laonstory.ysu.domain.common.BaseTimeEntity;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_COMMENT")
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> children = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CommentLike> likes = new ArrayList<>();

    public void removeChild(Comment comment) {
        this.children.remove(comment);
    }

}
