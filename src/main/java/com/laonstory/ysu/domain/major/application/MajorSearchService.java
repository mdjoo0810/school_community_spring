package com.laonstory.ysu.domain.major.application;

import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.college.exception.CollegeNotFoundException;
import com.laonstory.ysu.domain.college.persistence.CollegeRepositorySupport;
import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.major.dto.MajorResponse;
import com.laonstory.ysu.domain.major.persistence.MajorRepositorySupport;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndFreeAndAuditResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MajorSearchService {

    private final CollegeRepositorySupport collegeRepositorySupport;
    private final MajorRepositorySupport majorRepositorySupport;
    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;

    public List<MajorResponse> findAllByCollegeId(Long collegeId) {

        if (collegeRepositorySupport.findById(collegeId) == null) {
            throw new CollegeNotFoundException(collegeId);
        }

        List<Major> majorList = majorRepositorySupport.findAllByCollegeId(collegeId);

        return majorList.stream().map(MajorResponse::new).collect(Collectors.toList());
    }

    public OrganizationWithNoticeAndActivityAndFreeAndAuditResponse findByOgzNoticeSearchModel(OgzNoticeSearchModel model) {
        Major major = majorRepositorySupport.findById(model.getOgzId());

        return new OrganizationWithNoticeAndActivityAndFreeAndAuditResponse(major,
                getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE)),
                getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.FREE)),
                getOgzNoticeByModelAndMenusLimit_3(model, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT)));
    }

    private List<OgzNotice> getOgzNoticeByModelAndMenusLimit_3(OgzNoticeSearchModel model, List<OgzNoticeMenu> menus) {
        return ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel(
                new OgzNoticeSearchModel(model, menus));
    }
}
