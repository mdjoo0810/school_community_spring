package com.laonstory.ysu.domain.college.application;

import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.college.dto.CollegeResponse;
import com.laonstory.ysu.domain.college.persistance.CollegeJpaRepository;
import com.laonstory.ysu.domain.college.persistance.CollegeRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollegeSearchService {

    private final CollegeJpaRepository collegeJpaRepository;

    private final CollegeRepositorySupport collegeRepositorySupport;

    public List<CollegeResponse> findAll() {
        List<College> collegeList = collegeRepositorySupport.findAll();

        return collegeList.stream().map(CollegeResponse::new).collect(Collectors.toList());

    }

}
