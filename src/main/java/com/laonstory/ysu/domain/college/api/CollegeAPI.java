package com.laonstory.ysu.domain.college.api;

import com.laonstory.ysu.domain.college.application.CollegeSearchService;
import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.college.dto.CollegeResponse;
import com.laonstory.ysu.global.common.response.ApiListResponse;
import com.laonstory.ysu.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/college")
@RequiredArgsConstructor
public class CollegeAPI {

    private final CollegeSearchService collegeSearchService;

    @GetMapping
    public ApiListResponse<CollegeResponse> findAll() {
        return new ApiListResponse<>(HttpStatus.OK, collegeSearchService.findAll());
    }

}