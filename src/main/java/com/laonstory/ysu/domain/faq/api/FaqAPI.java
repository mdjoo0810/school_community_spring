package com.laonstory.ysu.domain.faq.api;

import com.laonstory.ysu.domain.faq.application.FaqSearchService;
import com.laonstory.ysu.domain.faq.dto.FaqResponse;
import com.laonstory.ysu.global.common.response.ApiListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqAPI {

    private final FaqSearchService faqSearchService;

    @GetMapping("{categoryId}")
    public ApiListResponse<FaqResponse> findAllByCategoryId(@PathVariable Long categoryId) {
        return new ApiListResponse<>(HttpStatus.OK, faqSearchService.findAllByCategoryId(categoryId));
    }

}
