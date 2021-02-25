package com.laonstory.ysu.domain.studentOGZ.api;

import com.laonstory.ysu.domain.organization.application.OrganizationSearchService;
import com.laonstory.ysu.domain.organization.dto.*;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.laonstory.ysu.domain.studentOGZ.application.OgzSearchService;
import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import com.laonstory.ysu.global.common.response.ApiPagingResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OgzAPI {

    private final OgzSearchService ogzSearchService;
    private final OrganizationSearchService organizationSearchService;
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

    @GetMapping("/council/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchCouncilNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        StudentOgz ogz = ogzSearchService.findOgz(StudentOgz.OgzType.COUNCIL);

        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.COUNCIL)
                .ogzId(ogz.getId())
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

    @GetMapping("/sasaeng/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchSasaengNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        StudentOgz ogz = ogzSearchService.findOgz(StudentOgz.OgzType.SASAENG);

        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.ORGANIZATION)
                .ogzId(ogz.getId())
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

    @GetMapping("/chunchu/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchChunchuNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        StudentOgz ogz = ogzSearchService.findOgz(StudentOgz.OgzType.CHUNCHU);

        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.ORGANIZATION)
                .ogzId(ogz.getId())
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

    @GetMapping("/ymbs/notices/search")
    public ApiPagingResponse<OgzNoticeResponse> searchYMBSNotice(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String year,
            @RequestParam String menu
    ) {
        StudentOgz ogz = ogzSearchService.findOgz(StudentOgz.OgzType.YMBS);

        OgzNoticeSearchModel model = OgzNoticeSearchModel.builder()
                .isWhole(false)
                .tabType(OgzTabType.ORGANIZATION)
                .ogzId(ogz.getId())
                .year(year)
                .menus(Collections.singletonList(OgzNoticeMenu.valueOf(menu)))
                .query(query)
                .build();

        return new ApiPagingResponse<>(HttpStatus.OK, organizationSearchService.searchNotice(model, page));
    }

}
