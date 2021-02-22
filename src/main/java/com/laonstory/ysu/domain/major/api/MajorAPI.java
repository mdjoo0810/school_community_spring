package com.laonstory.ysu.domain.major.api;

import com.laonstory.ysu.domain.major.application.MajorSearchService;
import com.laonstory.ysu.domain.major.dto.MajorResponse;
import com.laonstory.ysu.global.common.response.ApiListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/major")
@RequiredArgsConstructor
public class MajorAPI {

    private final MajorSearchService majorSearchService;

    @GetMapping("/college/{collegeId}")
    public ApiListResponse<MajorResponse> findAllByCollegeId(@PathVariable Long collegeId) {
        return new ApiListResponse<>(HttpStatus.OK, majorSearchService.findAllByCollegeId(collegeId));
    }

}
