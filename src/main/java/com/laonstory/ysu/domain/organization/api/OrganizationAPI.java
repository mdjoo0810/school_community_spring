package com.laonstory.ysu.domain.organization.api;

import com.laonstory.ysu.domain.comment.dto.CommentWithChildrenResponse;
import com.laonstory.ysu.domain.organization.application.OrganizationSearchService;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeDetailResponse;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationAPI {

    private final OrganizationSearchService organizationSearchService;

    @GetMapping("/notice/{noticeId}")
    public ApiResponse<OgzNoticeDetailResponse> findById(
            @AuthenticationPrincipal User user,
            @PathVariable Long noticeId) {
        return new ApiResponse<>(HttpStatus.OK, organizationSearchService.findById(noticeId, user));
    }

    @GetMapping("/notice/{noticeId}/comments")
    public ApiPagingResponse<CommentWithChildrenResponse> findAllCommentByNoticeId(
            @AuthenticationPrincipal User user,
            @PathVariable Long noticeId,
            @RequestParam(required = false, defaultValue = "1") int page
    ) {
        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.findAllCommentByNoticeId(user, noticeId, page));
    }

}
