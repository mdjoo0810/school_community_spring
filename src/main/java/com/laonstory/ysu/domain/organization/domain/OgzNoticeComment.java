package com.laonstory.ysu.domain.organization.domain;

import com.laonstory.ysu.domain.comment.domain.Comment;
import com.laonstory.ysu.domain.comment.dto.CommentRequest;
import com.laonstory.ysu.domain.notice.domain.Notice;
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

//    public static OgzNoticeComment create(CommentRequest dto, User user, OgzNotice notice) {
//        OgzNoticeComment comment = (OgzNoticeComment) OgzNoticeComment.create(dto,user);
//        comment.notice = notice;
//        return comment;
//    }
}
