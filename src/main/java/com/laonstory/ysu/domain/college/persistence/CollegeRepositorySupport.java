package com.laonstory.ysu.domain.college.persistence;

import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.college.exception.CollegeNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.laonstory.ysu.domain.college.domain.QCollege.college;

@Repository
public class CollegeRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CollegeRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(College.class);
        this.queryFactory = jpaQueryFactory;
    }

    public College findById(Long collegeId) {
        College result = queryFactory
                .selectFrom(college)
                .where(college.id.eq(collegeId))
                .fetchOne();

        if (result == null) throw new CollegeNotFoundException(collegeId);

        return result;
    }

    public List<College> findAll() {
        return queryFactory.selectFrom(college).fetch();
    }

}
