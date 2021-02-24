package com.laonstory.ysu.domain.faq.application;

import com.laonstory.ysu.domain.faq.domain.Faq;
import com.laonstory.ysu.domain.faq.dto.FaqResponse;
import com.laonstory.ysu.domain.faq.persistence.FaqJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FaqSearchService {

    private final FaqJpaRepository faqJpaRepository;

    public List<FaqResponse> findAllByCategoryId(Long categoryId) {
        List<Faq> list = faqJpaRepository.findAllByCategoryId(categoryId);

        return list.stream().map(FaqResponse::new).collect(Collectors.toList());
    }

}
