package com.laonstory.ysu.domain.notice.persistence;

import com.laonstory.ysu.domain.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeJpaRepository extends JpaRepository<Notice, Long> {
}
