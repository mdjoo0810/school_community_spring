package com.laonstory.ysu.domain.point.api;

import com.laonstory.ysu.domain.point.application.PointSearchService;
import com.laonstory.ysu.domain.point.dto.PointHistoryResponse;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointAPI {

    private final PointSearchService pointSearchService;

    @GetMapping("/me")
    public ApiPagingResponse<PointHistoryResponse> findByUser(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "1") int page) {
        return new ApiPagingResponse<>(HttpStatus.OK, pointSearchService.findByUserId(user, page));
    }

}
