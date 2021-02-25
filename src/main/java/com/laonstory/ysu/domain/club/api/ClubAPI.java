package com.laonstory.ysu.domain.club.api;

import com.laonstory.ysu.domain.club.application.ClubCategorySearchService;
import com.laonstory.ysu.domain.club.application.ClubSearchService;
import com.laonstory.ysu.domain.club.dto.ClubCategoryResponse;
import com.laonstory.ysu.domain.club.dto.ClubWithNoticeAndAlbumAndAuditResponse;
import com.laonstory.ysu.domain.club.dto.ClubWithNoticeResponse;
import com.laonstory.ysu.domain.club.model.ClubSearchModel;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
import com.laonstory.ysu.global.common.response.ApiListResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubAPI {

    private final ClubCategorySearchService categorySearchService;
    private final ClubSearchService searchService;

    @GetMapping("/categories")
    public ApiListResponse<ClubCategoryResponse> findAllByIsAssociationIsFalse() {
        return new ApiListResponse<>(HttpStatus.OK, categorySearchService.findAllByIsAssociationIsFalse());
    }

    @GetMapping
    public ApiResponse<ClubWithNoticeResponse> findAll_CNR(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String query
    ) {
        return new ApiResponse<>(HttpStatus.OK, searchService.findNoticesWithClubs(new ClubSearchModel(categoryId, query)));
    }

    @GetMapping("/association")
    public ApiResponse<OrganizationWithNoticeAndActivityAndAuditResponse> findAssociation_NAAR() {
        return new ApiResponse<>(HttpStatus.OK, searchService.findAssociation_NAAR());
    }

    @GetMapping("/{clubId}")
    public ApiResponse<ClubWithNoticeAndAlbumAndAuditResponse> find_NAAR(@PathVariable Long clubId) {
        return new ApiResponse<>(HttpStatus.OK, searchService.find_NAAR(clubId));
    }

}
