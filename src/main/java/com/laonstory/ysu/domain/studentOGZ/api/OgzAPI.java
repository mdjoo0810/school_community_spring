package com.laonstory.ysu.domain.studentOGZ.api;

import com.laonstory.ysu.domain.organization.dto.OrganizationResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndNewsResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.laonstory.ysu.domain.studentOGZ.application.OgzSearchService;
import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OgzAPI {

    private final OgzSearchService ogzSearchService;

    @GetMapping("/council")
    public ApiResponse<OrganizationWithNoticeAndActivityAndAuditResponse> findCouncil() {
        return new ApiResponse<>(HttpStatus.OK,
                ogzSearchService.findByOgzNoticeSearchModel_ONAAR(new OgzNoticeSearchModel(StudentOgz.OgzType.COUNCIL, OgzTabType.COUNCIL)));
    }

    @GetMapping("/sasaeng")
    public ApiResponse<OrganizationWithNoticeAndActivityAndAuditResponse> findSasaeng() {
        return new ApiResponse<>(HttpStatus.OK,
                ogzSearchService.findByOgzNoticeSearchModel_ONAAR(new OgzNoticeSearchModel(StudentOgz.OgzType.SASAENG, OgzTabType.ORGANIZATION)));
    }

    @GetMapping("/chunchu")
    public ApiResponse<OrganizationWithNoticeAndNewsResponse> findChunchu() {
        return new ApiResponse<>(HttpStatus.OK,
                ogzSearchService.findByOgzNoticeSearchModel_ONNR(new OgzNoticeSearchModel(StudentOgz.OgzType.CHUNCHU, OgzTabType.ORGANIZATION)));
    }


    @GetMapping("/ymbs")
    public ApiResponse<OrganizationWithNoticeResponse> findYMBS() {
        return new ApiResponse<>(HttpStatus.OK,
                ogzSearchService.findByOgzNoticeSearchModel_ONR(new OgzNoticeSearchModel(StudentOgz.OgzType.YMBS, OgzTabType.ORGANIZATION)));
    }

}
