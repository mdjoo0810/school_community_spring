package com.laonstory.ysu.domain.organization.persistence;

import com.laonstory.ysu.domain.organization.domain.OgzNoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgzNoticeCommentJpaRepository extends JpaRepository<OgzNoticeComment, Long> {
}
