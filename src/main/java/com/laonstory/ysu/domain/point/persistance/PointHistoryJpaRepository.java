package com.laonstory.ysu.domain.point.persistance;

import com.laonstory.ysu.domain.point.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {
}
