package com.laonstory.ysu.domain.club.persistence;

import com.laonstory.ysu.domain.club.domain.ClubAlbum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.ysu.domain.club.domain.QClubAlbum.clubAlbum;

@Repository
public class ClubAlbumRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public ClubAlbumRepositorySupport(JPAQueryFactory queryFactory) {
        super(ClubAlbum.class);
        this.queryFactory = queryFactory;
    }

    public List<ClubAlbum> findLimit_3ByClubId(Long clubId) {
        return queryFactory
                .selectFrom(clubAlbum)
                .where(clubAlbum.club.id.eq(clubId))
                .orderBy(clubAlbum.createdDate.desc())
                .limit(3)
                .fetch();
    }
}
