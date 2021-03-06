package com.laonstory.ysu.domain.association.application;

import com.laonstory.ysu.domain.association.domain.Association;
import com.laonstory.ysu.domain.association.dto.AssociationWithNoticeAndActivityWithAuditResponse;
import com.laonstory.ysu.domain.association.dto.AssociationWithTagsResponse;
import com.laonstory.ysu.domain.association.dto.AssociationsWithNoticesResponse;
import com.laonstory.ysu.domain.association.model.AssociationSearchModel;
import com.laonstory.ysu.domain.association.persistence.AssociationRepositorySupport;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import com.laonstory.ysu.global.common.request.PageRequest;
import com.laonstory.ysu.global.common.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
public class AssociationSearchService {

    private final AssociationRepositorySupport associationRepositorySupport;
    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;

    public AssociationsWithNoticesResponse findAssociationsAndNotices(AssociationSearchModel model) {

        List<OgzNotice> notices = ogzNoticeRepositorySupport
                .findLimit_3ByOgzNoticeSearchModelAndIsWholeTrue(
                        new OgzNoticeSearchModel(OgzTabType.ASSOCIATION, Collections.singletonList(OgzNoticeMenu.NOTICE)));
        List<Association> associations = associationRepositorySupport
                .findLimit_3BySearchModelOrderByIdDesc(model);

        return new AssociationsWithNoticesResponse(associations, notices);
    }

    public AssociationWithNoticeAndActivityWithAuditResponse findById_NAAR(Long associationId) {
        Association association = associationRepositorySupport.findById(associationId);

        List<OgzNotice> notices = ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel(
                new OgzNoticeSearchModel(OgzTabType.ASSOCIATION, Collections.singletonList(OgzNoticeMenu.NOTICE), associationId));
        List<OgzNotice> activityWithAudit = ogzNoticeRepositorySupport.findLimit_3ByOgzNoticeSearchModel(
                new OgzNoticeSearchModel(OgzTabType.ASSOCIATION, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT), associationId));

        return new AssociationWithNoticeAndActivityWithAuditResponse(association, notices, activityWithAudit);
    }

    public PagingResponse<AssociationWithTagsResponse> search(AssociationSearchModel model, int page) {

        PageRequest pageRequest = new PageRequest(page, 10);

        Page<Association> associations = associationRepositorySupport.findBySearchModelWithPaging(model, pageRequest.of());

        List<AssociationWithTagsResponse> responses = associations.getContent()
                .stream().map(AssociationWithTagsResponse::new).collect(Collectors.toList());

        return new PagingResponse<>(page, associations.getTotalPages(), associations.getTotalElements(),responses);

    }

}
