package com.laonstory.ysu.domain.organization.dto;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrganizationWithNoticeAndActivityAndFreeAndAuditResponse {

    private OrganizationResponse organization;
    private List<OgzNoticeResponse> notices;
    private List<OgzNoticeResponse> frees;
    private List<OgzNoticeResponse> activitiesWithAudits;

    public OrganizationWithNoticeAndActivityAndFreeAndAuditResponse(Organization organization,
                                                                    List<OgzNotice> notices,
                                                                    List<OgzNotice> frees,
                                                                    List<OgzNotice> activitiesWithAudits) {
        this.organization = new OrganizationResponse(organization);
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.frees = frees.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.activitiesWithAudits = activitiesWithAudits.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
    }

}
