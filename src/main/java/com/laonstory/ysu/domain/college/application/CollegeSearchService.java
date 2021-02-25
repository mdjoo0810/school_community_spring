package com.laonstory.ysu.domain.college.application;

import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.college.dto.CollegeResponse;
import com.laonstory.ysu.domain.college.persistence.CollegeJpaRepository;
import com.laonstory.ysu.domain.college.persistence.CollegeRepositorySupport;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollegeSearchService {

    private final CollegeRepositorySupport collegeRepositorySupport;

    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;

    public List<CollegeResponse> findAll() {
        List<College> collegeList = collegeRepositorySupport.findAll();

        return collegeList.stream().map(CollegeResponse::new).collect(Collectors.toList());

    }

    public OrganizationWithNoticeAndActivityAndAuditResponse findByOgzNoticeSearchModel(OgzNoticeSearchModel model) {
        College college = collegeRepositorySupport.findById(model.getOgzId());

        List<OgzNotice> notices = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE));
        List<OgzNotice> activitiesWithAudit = getOgzNoticeByModelAndMenusLimit_3(model, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT));

        return new OrganizationWithNoticeAndActivityAndAuditResponse(college, notices, activitiesWithAudit);
    }

    private List<OgzNotice> getOgzNoticeByModelAndMenusLimit_3(OgzNoticeSearchModel model, List<OgzNoticeMenu> menus) {
        return ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel(
                new OgzNoticeSearchModel(model, menus));
    }

}
