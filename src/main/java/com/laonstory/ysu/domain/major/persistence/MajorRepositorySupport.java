package com.laonstory.ysu.domain.major.persistence;

import com.laonstory.ysu.domain.major.domain.Major;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.ysu.domain.major.domain.QMajor.major;

@Repository
public class MajorRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MajorRepositorySupport(JPAQueryFactory queryFactory) {
        super(Major.class);
        this.queryFactory = queryFactory;
    }

    public Major findById(Long majorId) {
        return queryFactory
                .selectFrom(major)
                .where(major.id.eq(majorId))
                .fetchOne();
    }

    public List<Major> findAllByCollegeId(Long collegeId) {
        return queryFactory
                .selectFrom(major)
                .where(major.college.id.eq(collegeId))
                .fetch();
    }
}
