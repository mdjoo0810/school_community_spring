package com.laonstory.ysu.domain.faq.application;

import com.laonstory.ysu.domain.faq.domain.FaqCategory;
import com.laonstory.ysu.domain.faq.dto.FaqCategoryResponse;
import com.laonstory.ysu.domain.faq.persistence.FaqCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FaqCategorySearchService {

    private final FaqCategoryJpaRepository faqCategoryJpaRepository;

    public List<FaqCategoryResponse> findAll() {
        List<FaqCategory> list = faqCategoryJpaRepository.findAll();
        return list.stream().map(FaqCategoryResponse::new).collect(Collectors.toList());
    }

}
