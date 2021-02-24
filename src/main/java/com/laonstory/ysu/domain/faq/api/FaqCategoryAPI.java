package com.laonstory.ysu.domain.faq.api;

import com.laonstory.ysu.domain.faq.application.FaqCategorySearchService;
import com.laonstory.ysu.domain.faq.dto.FaqCategoryResponse;
import com.laonstory.ysu.global.common.response.ApiListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/faq/category")
@RequiredArgsConstructor
public class FaqCategoryAPI {

    private final FaqCategorySearchService faqCategorySearchService;

    @GetMapping
    public ApiListResponse<FaqCategoryResponse> findAll() {
        return new ApiListResponse<>(HttpStatus.OK, faqCategorySearchService.findAll());
    }

}
