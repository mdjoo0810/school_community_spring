package com.laonstory.ysu.domain.organization.application;

import com.laonstory.ysu.domain.comment.dto.CommentRequest;
import com.laonstory.ysu.domain.comment.dto.CommentResponse;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.OgzNoticeComment;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeCommentJpaRepository;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeCommentRepositorySupport;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeLikeRepositorySupport;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.global.error.exception.NotMyEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OgzNoticeCommentService {

    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;
    private final OgzNoticeCommentJpaRepository ogzNoticeCommentJpaRepository;
    private final OgzNoticeCommentRepositorySupport ogzNoticeCommentRepositorySupport;

    public CommentResponse save(CommentRequest dto, User user, Long noticeId) {
        OgzNotice notice = ogzNoticeRepositorySupport.findById(noticeId);

        OgzNoticeComment comment = OgzNoticeComment.createWithParent(dto
                , user
                , dto.getParent() != null ? ogzNoticeCommentRepositorySupport.findById(dto.getParent()) : null
                , notice);

        ogzNoticeCommentJpaRepository.save(comment);

        return new CommentResponse(comment, user);

    }

    public CommentResponse update(CommentRequest dto, User user, Long commentId) {
        OgzNoticeComment comment = ogzNoticeCommentRepositorySupport.findById(commentId);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NotMyEntityException(user.getNickname());
        }

        comment.update(dto.getContent());

        return new CommentResponse(comment, user);
    }

    public String delete(User user, Long noticeId, Long commentId) {
        OgzNotice notice = ogzNoticeRepositorySupport.findById(noticeId);

        OgzNoticeComment comment = ogzNoticeCommentRepositorySupport.findById(commentId);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NotMyEntityException(user.getNickname());
        }

        if (comment.getParent() != null) {
            comment.getParent().removeChild(comment);
        } else {
            notice.removeComment(comment);
        }

        return "REMOVED";
    }

}
