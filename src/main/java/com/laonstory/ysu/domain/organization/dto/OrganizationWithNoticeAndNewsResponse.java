package com.laonstory.ysu.domain.organization.dto;

import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrganizationWithNoticeAndNewsResponse {
    private OrganizationResponse organization;
    private List<OgzNoticeResponse> notices;
    private List<OgzNoticeResponse> news;

    public OrganizationWithNoticeAndNewsResponse(Organization organization, List<OgzNotice> notices, List<OgzNotice> news) {
        this.organization = new OrganizationResponse(organization);
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.news = news.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
    }
}
