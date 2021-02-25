package com.laonstory.ysu.domain.studentOGZ.application;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.*;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import com.laonstory.ysu.domain.studentOGZ.domain.StudentOgz;
import com.laonstory.ysu.domain.studentOGZ.persistence.StudentOgzRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public StudentOgz findOgz (StudentOgz.OgzType type) {
        return studentOgzRepositorySupport.findByType(type);
    }

    public OrganizationWithNoticeResponse findByOgzNoticeSearchModel_ONR(OgzNoticeSearchModel model) {
        StudentOgz ogz = studentOgzRepositorySupport.findByType(model.getOgzType());

        List<OgzNotice> notices = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE));

        return new OrganizationWithNoticeResponse(ogz, notices);
    }

    public OrganizationWithNoticeAndNewsResponse findByOgzNoticeSearchModel_ONNR(OgzNoticeSearchModel model) {
        StudentOgz ogz = studentOgzRepositorySupport.findByType(model.getOgzType());

        List<OgzNotice> notices = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE));
        List<OgzNotice> news = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE));

        return new OrganizationWithNoticeAndNewsResponse(ogz, notices, news);
    }



    public OrganizationWithNoticeAndActivityAndAuditResponse findByOgzNoticeSearchModel_ONAAR(OgzNoticeSearchModel model) {
        StudentOgz ogz = studentOgzRepositorySupport.findByType(model.getOgzType());

        List<OgzNotice> notices = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE));
        List<OgzNotice> activitiesAndAudits = getOgzNoticeByModelAndMenusLimit_3(model, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT));

        return new OrganizationWithNoticeAndActivityAndAuditResponse(ogz, notices, activitiesAndAudits);
    }

    public OrganizationWithNoticeAndActivityAndFreeAndAuditResponse findByOgzNoticeSearchModel_ONAFAR(OgzNoticeSearchModel model) {
        StudentOgz ogz = studentOgzRepositorySupport.findByType(model.getOgzType());

        List<OgzNotice> notices = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.NOTICE));
        List<OgzNotice> frees = getOgzNoticeByModelAndMenusLimit_3(model, Collections.singletonList(OgzNoticeMenu.FREE));
        List<OgzNotice> activitiesAndAudits = getOgzNoticeByModelAndMenusLimit_3(model, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT));



        return new OrganizationWithNoticeAndActivityAndFreeAndAuditResponse(ogz, notices, frees, activitiesAndAudits);
    }

    private List<OgzNotice> getOgzNoticeByModelAndMenusLimit_3(OgzNoticeSearchModel model, List<OgzNoticeMenu> menus) {
        return ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel(
                new OgzNoticeSearchModel(model, menus));
    }

}
