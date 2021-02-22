package com.laonstory.ysu.domain.user.persistance;

import com.laonstory.ysu.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.laonstory.ysu.domain.user.domain.QUser.user;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    public Boolean existByStudentId(String studentId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(user)
                .where(user.studentID.eq(studentId))
                .fetchFirst();

        return fetchOne != null;
    }

    public Boolean existByEmail(String email) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(user)
                .where(user.email.eq(email))
                .fetchFirst();

        return fetchOne != null;
    }
}
