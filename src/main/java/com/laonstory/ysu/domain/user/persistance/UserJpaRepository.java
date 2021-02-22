package com.laonstory.ysu.domain.user.persistance;

import com.laonstory.ysu.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserJpaRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
}
