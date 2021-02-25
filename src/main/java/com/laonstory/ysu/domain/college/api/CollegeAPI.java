package com.laonstory.ysu.domain.college.api;

import com.laonstory.ysu.domain.college.application.CollegeSearchService;
import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.college.dto.CollegeResponse;
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
@RequestMapping("/college")
@RequiredArgsConstructor
public class CollegeAPI {

    private final CollegeSearchService collegeSearchService;
    private final OrganizationSearchService organizationSearchService;

    @GetMapping
    public ApiListResponse<CollegeResponse> findAll() {
        return new ApiListResponse<>(HttpStatus.OK, collegeSearchService.findAll());
    }


    @GetMapping("/{collegeId}")
    public ApiResponse<OrganizationWithNoticeAndActivityAndAuditResponse> findByOgzNoticeSearchModel(@PathVariable Long collegeId) {
        return new ApiResponse<>(HttpStatus.OK, collegeSearchService.findByOgzNoticeSearchModel(new OgzNoticeSearchModel(OgzTabType.COLLEGE, collegeId)));
    }

    @GetMapping("/{collegeId}/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchNotice(
            @PathVariable Long collegeId,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.COLLEGE)
                .ogzId(collegeId)
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

}
