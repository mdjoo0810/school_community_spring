package com.laonstory.ysu.domain.notice.api;

import com.laonstory.ysu.domain.notice.application.NoticeSearchService;
import com.laonstory.ysu.domain.notice.dto.NoticeResponse;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeAPI {

    private final NoticeSearchService noticeSearchService;

    @GetMapping
    public ApiPagingResponse<NoticeResponse> findAll(@RequestParam(required = false, defaultValue = "1") int page) {
        return new ApiPagingResponse<>(HttpStatus.OK, noticeSearchService.findAll(page));
    }

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeResponse> findById(@PathVariable Long noticeId) {
        return new ApiResponse<>(HttpStatus.OK, noticeSearchService.findById(noticeId));
    }



}
