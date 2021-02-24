package com.laonstory.ysu.domain.studentOGZ.persistence;

import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import com.laonstory.ysu.global.error.exception.EntityNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.laonstory.ysu.domain.studentOGZ.domain.QStudentOgz.studentOgz;

@Repository
public class StudentOgzRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public StudentOgzRepositorySupport(JPAQueryFactory queryFactory) {
        super(StudentOgz.class);
        this.queryFactory = queryFactory;
    }

    public StudentOgz findByType(StudentOgz.OgzType type) {
        StudentOgz ogz = queryFactory
                .selectFrom(studentOgz)
                .where(studentOgz.type.eq(type))
                .fetchOne();

        if (ogz == null) throw new EntityNotFoundException(type.toString());

        return ogz;
    }
}
