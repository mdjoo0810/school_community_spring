package com.laonstory.ysu.domain.club.api;

import com.laonstory.ysu.domain.club.application.ClubCategorySearchService;
import com.laonstory.ysu.domain.club.application.ClubSearchService;
import com.laonstory.ysu.domain.club.domain.Club;
import com.laonstory.ysu.domain.club.dto.ClubCategoryResponse;
import com.laonstory.ysu.domain.club.dto.ClubResponse;
import com.laonstory.ysu.domain.club.dto.ClubWithNoticeAndAlbumAndAuditResponse;
import com.laonstory.ysu.domain.club.dto.ClubWithNoticeResponse;
import com.laonstory.ysu.domain.club.model.ClubSearchModel;
import com.laonstory.ysu.domain.organization.application.OrganizationSearchService;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.laonstory.ysu.global.common.response.ApiListResponse;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubAPI {

    private final ClubCategorySearchService categorySearchService;
    private final ClubSearchService searchService;
    private final OrganizationSearchService organizationSearchService;

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

    @GetMapping("/search")
    public ApiPagingResponse<ClubResponse> search(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query
    ) {
        return new ApiPagingResponse<>(HttpStatus.OK, searchService.search(new ClubSearchModel(query), page));
    }

    @GetMapping("/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchWholeNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query
    ) {
        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(true)
                .tabType(OgzTabType.CLUB)
                .menus(Collections.singletonList(OgzNoticeMenu.NOTICE))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

    @GetMapping("/association/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchAssociationNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        Club club = searchService.findAssociation();

        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.CLUB)
                .ogzId(club.getId())
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

    @GetMapping("/{associationId}/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchNotice(
            @PathVariable Long associationId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.CLUB)
                .ogzId(associationId)
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

}
