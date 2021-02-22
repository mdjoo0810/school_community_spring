package com.laonstory.ysu.domain.college.persistance;

import com.laonstory.ysu.domain.college.domain.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeJpaRepository extends JpaRepository<College, Long> {
}
