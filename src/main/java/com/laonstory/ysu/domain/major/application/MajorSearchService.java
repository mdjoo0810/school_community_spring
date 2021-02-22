package com.laonstory.ysu.domain.major.application;

import com.laonstory.ysu.domain.college.exception.CollegeNotFoundException;
import com.laonstory.ysu.domain.college.persistance.CollegeRepositorySupport;
import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.major.dto.MajorResponse;
import com.laonstory.ysu.domain.major.persistance.MajorRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MajorSearchService {

    private final CollegeRepositorySupport collegeRepositorySupport;
    private final MajorRepositorySupport majorRepositorySupport;

    public List<MajorResponse> findAllByCollegeId(Long collegeId) {

        if (collegeRepositorySupport.findById(collegeId) == null) {
            throw new CollegeNotFoundException(collegeId);
        }

        List<Major> majorList = majorRepositorySupport.findAllByCollegeId(collegeId);

        return majorList.stream().map(MajorResponse::new).collect(Collectors.toList());
    }

}
