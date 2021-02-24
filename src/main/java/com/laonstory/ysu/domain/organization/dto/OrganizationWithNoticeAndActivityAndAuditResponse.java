package com.laonstory.ysu.domain.organization.dto;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrganizationWithNoticeAndActivityAndAuditResponse {
    private OrganizationResponse organization;
    private List<OgzNoticeResponse> notices;
    private List<OgzNoticeResponse> activitiesWithAudits;

    public OrganizationWithNoticeAndActivityAndAuditResponse(Organization organization, List<OgzNotice> notices, List<OgzNotice> activitiesWithAudits) {
        this.organization = new OrganizationResponse(organization);
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.activitiesWithAudits = activitiesWithAudits.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
    }
}
