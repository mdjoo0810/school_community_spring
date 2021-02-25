package com.laonstory.ysu.domain.major.api;

import com.laonstory.ysu.domain.major.application.MajorSearchService;
import com.laonstory.ysu.domain.major.dto.MajorResponse;
import com.laonstory.ysu.domain.organization.application.OrganizationSearchService;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndFreeAndAuditResponse;
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

@Slf4j
@RestController
@RequestMapping("/major")
@RequiredArgsConstructor
public class MajorAPI {

    private final MajorSearchService majorSearchService;
    private final OrganizationSearchService organizationSearchService;

    @GetMapping("/college/{collegeId}")
    public ApiListResponse<MajorResponse> findAllByCollegeId(@PathVariable Long collegeId) {
        return new ApiListResponse<>(HttpStatus.OK, majorSearchService.findAllByCollegeId(collegeId));
    }

    @GetMapping("/{majorId}")
    public ApiResponse<OrganizationWithNoticeAndActivityAndFreeAndAuditResponse> findByOgzNoticeSearchModel (@PathVariable Long majorId) {
        return new ApiResponse<>(HttpStatus.OK, majorSearchService
                .findByOgzNoticeSearchModel(new OgzNoticeSearchModel(OgzTabType.MAJOR, majorId)));
    }

    @GetMapping("/{majorId}/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchNotice(
            @PathVariable Long majorId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.MAJOR)
                .ogzId(majorId)
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

}
