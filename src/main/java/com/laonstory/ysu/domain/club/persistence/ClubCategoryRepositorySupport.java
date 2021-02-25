package com.laonstory.ysu.domain.club.persistence;

import com.laonstory.ysu.domain.club.domain.ClubCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.ysu.domain.club.domain.QClubCategory.clubCategory;

@Repository
public class ClubCategoryRepositorySupport extends QuerydslRepositorySupport {

    JPAQueryFactory queryFactory;

    public ClubCategoryRepositorySupport(JPAQueryFactory queryFactory) {
        super(ClubCategory.class);
        this.queryFactory = queryFactory;
    }

    public List<ClubCategory> findAllByIsAssociationIsFalse() {
        return queryFactory
                .selectFrom(clubCategory)
                .where(clubCategory.isAssociation.isFalse())
                .orderBy(clubCategory.id.desc())
                .fetch();
    }

}
