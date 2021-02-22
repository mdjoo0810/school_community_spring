package com.laonstory.ysu.domain.major.persistance;

import com.laonstory.ysu.domain.major.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MajorJpaRepository extends JpaRepository<Major, Long> , QuerydslPredicateExecutor<Major> {
}
