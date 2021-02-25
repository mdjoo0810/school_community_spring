package com.laonstory.ysu.domain.organization.persistence;

import com.laonstory.ysu.domain.organization.domain.OgzNoticeComment;
import com.laonstory.ysu.domain.organization.exception.OgzNoticeCommentNotFoundException;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.laonstory.ysu.domain.organization.domain.QOgzNoticeComment.ogzNoticeComment;

@Repository
public class OgzNoticeCommentRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public OgzNoticeCommentRepositorySupport(JPAQueryFactory queryFactory) {
        super(OgzNoticeComment.class);
        this.queryFactory = queryFactory;
    }

    public OgzNoticeComment findById(Long id) {
        OgzNoticeComment result = queryFactory
                .selectFrom(ogzNoticeComment)
                .where(ogzNoticeComment.id.eq(id))
                .fetchOne();

        if (result == null) throw new OgzNoticeCommentNotFoundException(id);

        return result;
    }

    public Page<OgzNoticeComment> findAllByNoticeId(Long noticeId, Pageable pageable) {
        QueryResults<OgzNoticeComment> results = queryFactory
                .selectFrom(ogzNoticeComment)
                .where(ogzNoticeComment.notice.id.eq(noticeId), ogzNoticeComment.parent.isNull())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(ogzNoticeComment.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
