package com.laonstory.ysu.domain.organization.domain;

import com.laonstory.ysu.domain.comment.domain.Comment;
import com.laonstory.ysu.domain.comment.dto.CommentRequest;
import com.laonstory.ysu.domain.notice.domain.Notice;
import com.laonstory.ysu.domain.organization.application.OgzNoticeCommentService;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "T_OGZ_NOTICE_COMMENT")
public class OgzNoticeComment extends Comment {

    @ManyToOne(fetch = FetchType.LAZY)
    private OgzNotice notice;

    public static OgzNoticeComment createWithParent(CommentRequest dto, User user , OgzNoticeComment parent, OgzNotice notice) {
        OgzNoticeComment comment = new OgzNoticeComment();
        comment.setContent(dto.getContent());
        comment.setUser(user);
        comment.setParent(parent);
        comment.notice = notice;
        return comment;
    }

    public void update(String content) {
        this.setContent(content);
    }
}
