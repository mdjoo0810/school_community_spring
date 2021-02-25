package com.laonstory.ysu.domain.association.dto;

import com.laonstory.ysu.domain.association.domain.Association;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AssociationWithNoticeAndActivityWithAuditResponse {

    private AssociationWithTagsResponse association;
    private List<OgzNoticeResponse> notices;
    private List<OgzNoticeResponse> activitiesWithAudits;

    public AssociationWithNoticeAndActivityWithAuditResponse(Association association, List<OgzNotice> notices, List<OgzNotice> activitiesWithAudits) {
        this.association = new AssociationWithTagsResponse(association);
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.activitiesWithAudits = activitiesWithAudits.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
    }

}
