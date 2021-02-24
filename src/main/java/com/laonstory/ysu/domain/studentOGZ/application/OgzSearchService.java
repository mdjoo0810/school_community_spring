package com.laonstory.ysu.domain.studentOGZ.application;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import com.laonstory.ysu.domain.studentOGZ.persistence.StudentOgzRepositorySupport;
import com.laonstory.ysu.global.common.request.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OgzSearchService {

    private final StudentOgzRepositorySupport studentOgzRepositorySupport;

    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;

    public OrganizationWithNoticeAndActivityAndAuditResponse findByOgzNoticeSearchModel(OgzNoticeSearchModel model) {
        StudentOgz ogz = studentOgzRepositorySupport.findByType(model.getOgzType());

        List<OgzNotice> notices = ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel(
                new OgzNoticeSearchModel(model, Collections.singletonList(OgzNoticeMenu.NOTICE)));

        List<OgzNotice> activitiesAndAudits = ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel
                (new OgzNoticeSearchModel(model, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT)));

        return new OrganizationWithNoticeAndActivityAndAuditResponse(ogz, notices, activitiesAndAudits);
    }

}
