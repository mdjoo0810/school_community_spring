package com.laonstory.ysu.domain.association.dto;

import com.laonstory.ysu.domain.association.domain.Association;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.Organization;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AssociationsWithNoticesResponse {

    private List<OgzNoticeResponse> notices;
    private List<AssociationWithTagsResponse> associations;

    public AssociationsWithNoticesResponse(List<Association> associations, List<OgzNotice> notices) {
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.associations = associations.stream().map(AssociationWithTagsResponse::new).collect(Collectors.toList());
    }

}
