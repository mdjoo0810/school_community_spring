package com.laonstory.ysu.domain.association.api;

import com.laonstory.ysu.domain.association.application.AssociationSearchService;
import com.laonstory.ysu.domain.association.dto.AssociationWithNoticeAndActivityWithAuditResponse;
import com.laonstory.ysu.domain.association.dto.AssociationsWithNoticesResponse;
import com.laonstory.ysu.domain.association.model.AssociationSearchModel;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/association")
public class AssociationAPI {

    private final AssociationSearchService searchService;

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

}
