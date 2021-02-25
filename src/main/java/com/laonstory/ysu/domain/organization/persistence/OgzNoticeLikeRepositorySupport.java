package com.laonstory.ysu.domain.organization.persistence;

import com.laonstory.ysu.domain.organization.domain.OgzNoticeLike;
import com.laonstory.ysu.domain.organization.exception.OgzNoticeNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.laonstory.ysu.domain.organization.domain.QOgzNoticeLike.ogzNoticeLike;

@Repository
public class OgzNoticeLikeRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public OgzNoticeLikeRepositorySupport(JPAQueryFactory queryFactory) {
        super(OgzNoticeLike.class);
        this.queryFactory = queryFactory;
    }

    public OgzNoticeLike findById(Long likeId) {
        OgzNoticeLike result = queryFactory
                .selectFrom(ogzNoticeLike)
                .where(ogzNoticeLike.id.eq(likeId))
                .fetchOne();

        if (result == null) throw new OgzNoticeNotFoundException(likeId);

        return result;
    }
}
