package com.laonstory.ysu.domain.organization.persistence;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgzNoticeJpaRepository extends JpaRepository<OgzNotice, Long> {
}
