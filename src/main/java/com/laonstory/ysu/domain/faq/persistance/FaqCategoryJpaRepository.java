package com.laonstory.ysu.domain.faq.persistance;

import com.laonstory.ysu.domain.faq.domain.FaqCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqCategoryJpaRepository extends JpaRepository<FaqCategory, Long> {
}
