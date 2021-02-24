package com.laonstory.ysu.domain.faq.persistance;

import com.laonstory.ysu.domain.faq.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqJpaRepository extends JpaRepository<Faq , Long> {

    List<Faq> findAllByCategoryId(Long categoryId);

}
