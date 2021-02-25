package com.laonstory.ysu.domain.association.api;

import com.laonstory.ysu.domain.association.application.AssociationSearchService;
import com.laonstory.ysu.domain.association.dto.AssociationWithNoticeAndActivityWithAuditResponse;
import com.laonstory.ysu.domain.association.dto.AssociationWithTagsResponse;
import com.laonstory.ysu.domain.association.dto.AssociationsWithNoticesResponse;
import com.laonstory.ysu.domain.association.model.AssociationSearchModel;
import com.laonstory.ysu.domain.organization.application.OrganizationSearchService;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/association")
public class AssociationAPI {

    private final AssociationSearchService searchService;
    private final OrganizationSearchService organizationSearchService;

    @GetMapping()
    public ApiResponse<AssociationsWithNoticesResponse> findAssociationWithNotices(
            @RequestParam(required = false) Long collegeId,
            @RequestParam(required = false) Long majorId
    ) {
        return new ApiResponse<>(HttpStatus.OK, searchService.findAssociationsAndNotices(new AssociationSearchModel(collegeId, majorId)));
    }

    @GetMapping("/{associationId}")
    public ApiResponse<AssociationWithNoticeAndActivityWithAuditResponse> findById_NAAR(@PathVariable Long associationId) {
        return new ApiResponse<>(HttpStatus.OK, searchService.findById_NAAR(associationId));
    }

    @GetMapping("/search")
    public ApiPagingResponse<AssociationWithTagsResponse> search(
            @RequestParam(required = false) String query,
            @RequestParam(required = false, defaultValue = "1") int page
    ) {
        return new ApiPagingResponse<>(HttpStatus.OK, searchService.search(new AssociationSearchModel(query), page));
    }

    @GetMapping("/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchWholeNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query
    ) {
        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(true)
                .tabType(OgzTabType.ASSOCIATION)
                .menus(Collections.singletonList(OgzNoticeMenu.NOTICE))
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
                .tabType(OgzTabType.ASSOCIATION)
                .ogzId(associationId)
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

}
