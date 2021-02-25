package com.laonstory.ysu.domain.organization.api;

import com.laonstory.ysu.domain.comment.dto.CommentRequest;
import com.laonstory.ysu.domain.comment.dto.CommentResponse;
import com.laonstory.ysu.domain.comment.dto.CommentWithChildrenResponse;
import com.laonstory.ysu.domain.organization.application.OgzNoticeCommentService;
import com.laonstory.ysu.domain.organization.application.OgzNoticeLikeService;
import com.laonstory.ysu.domain.organization.application.OrganizationSearchService;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeDetailResponse;
import com.laonstory.ysu.domain.user.domain.User;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationAPI {

    private final OrganizationSearchService organizationSearchService;
    private final OgzNoticeLikeService ogzNoticeLikeService;
    private final OgzNoticeCommentService ogzNoticeCommentService;

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

    @PostMapping("/notice/{noticeId}/like")
    public ApiResponse<String> pushLike(
            @AuthenticationPrincipal User user,
            @PathVariable Long noticeId
    ) {
        return new ApiResponse<>(HttpStatus.OK, ogzNoticeLikeService.pushLike(noticeId, user));
    }

    @PostMapping("/notice/{noticeId}/comment")
    public ApiResponse<CommentResponse> saveComment(
            @PathVariable Long noticeId,
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CommentRequest dto
            ) {
        return new ApiResponse<>(HttpStatus.OK, ogzNoticeCommentService.save(dto,user,noticeId));
    }

    @PatchMapping("/notice/comment/{commentId}")
    public ApiResponse<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CommentRequest dto
    ) {
        return new ApiResponse<>(HttpStatus.OK, ogzNoticeCommentService.update(dto,user,commentId));
    }

    @DeleteMapping("/notice/{noticeId}/comment/{commentId}")
    public ApiResponse<String> deleteComment(
            @PathVariable Long noticeId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal User user
    ) {
        return new ApiResponse<>(HttpStatus.OK, ogzNoticeCommentService.delete(user,noticeId, commentId));
    }

}
