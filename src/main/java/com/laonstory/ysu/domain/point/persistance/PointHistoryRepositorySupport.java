package com.laonstory.ysu.domain.point.persistance;

import com.laonstory.ysu.domain.point.domain.PointHistory;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.laonstory.ysu.domain.point.domain.QPointHistory.pointHistory;

@Repository
public class PointHistoryRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public PointHistoryRepositorySupport(JPAQueryFactory queryFactory) {
        super(PointHistory.class);
        this.queryFactory = queryFactory;
    }

    public Page<PointHistory> findAllByUserId(Long userId, Pageable pageable) {
        QueryResults<PointHistory> result = queryFactory
                .selectFrom(pointHistory)
                .where(pointHistory.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(pointHistory.createdAt.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
