package com.laonstory.ysu.domain.notice.persistence;

import com.laonstory.ysu.domain.notice.domain.Notice;
import com.laonstory.ysu.domain.notice.exception.NoticeNotFoundException;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.laonstory.ysu.domain.notice.domain.QNotice.notice;

@Repository
public class NoticeRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public NoticeRepositorySupport(JPAQueryFactory queryFactory) {
        super(Notice.class);
        this.queryFactory = queryFactory;
    }

    public Page<Notice> findAll(Pageable pageable) {
        QueryResults<Notice> result = queryFactory
                .selectFrom(notice)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    public Notice findById(Long id) {
        Notice result = queryFactory
                .selectFrom(notice)
                .where(notice.id.eq(id))
                .fetchOne();

        if (result == null) throw new NoticeNotFoundException(id.toString());

        return result;
    }

}
