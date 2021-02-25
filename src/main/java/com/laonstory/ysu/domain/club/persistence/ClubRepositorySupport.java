package com.laonstory.ysu.domain.club.persistence;

import com.laonstory.ysu.domain.club.domain.Club;
import com.laonstory.ysu.domain.club.exception.ClubNotFoundException;
import com.laonstory.ysu.domain.club.model.ClubSearchModel;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.ysu.domain.club.domain.QClub.club;

@Repository
public class ClubRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public ClubRepositorySupport(JPAQueryFactory queryFactory) {
        super(Club.class);
        this.queryFactory = queryFactory;
    }

    public Club findById(Long clubId) {
        Club result = queryFactory
                .selectFrom(club)
                .where(club.id.eq(clubId))
                .fetchOne();

        if (result == null) throw new ClubNotFoundException(clubId);

        return result;
    }

    public Club findAssociation() {
        Club result = queryFactory
                .selectFrom(club)
                .where(club.category.isAssociation.isTrue())
                .orderBy(club.id.desc())
                .fetchFirst();

        if (result == null) throw new ClubNotFoundException(0L);

        return result;
    }

    public List<Club> findLimit_3BySearchModel(ClubSearchModel model) {
        return queryFactory
                .selectFrom(club)
                .where(eqCategory(model.getCategoryId()),
                        containsQuery(model.getQuery()),
                        club.category.isAssociation.isFalse())
                .limit(3)
                .orderBy(club.id.desc())
                .fetch();
    }

    public Page<Club> search(ClubSearchModel model, Pageable pageable) {
        QueryResults<Club> results = queryFactory
                .selectFrom(club)
                .where(containsQuery(model.getQuery()), club.category.isAssociation.isFalse())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(club.id.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    // ========================================
    // 조건
    // ========================================
    private BooleanExpression eqCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }

        return club.category.id.eq(categoryId);
    }

    private BooleanExpression containsQuery(String query) {
        if (query == null) {
            return null;
        }

        if (query.isBlank()) {
            return null;
        }

        return club.title.contains(query).or(club.description.contains(query)).or(club.tags.any().name.contains(query));
    }

}
