package com.laonstory.ysu.domain.association.dto;

import com.laonstory.ysu.domain.association.domain.Association;
import com.laonstory.ysu.domain.hashTag.dto.HashTagResponse;
import com.laonstory.ysu.domain.organization.domain.Organization;
import com.laonstory.ysu.domain.organization.dto.OrganizationResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AssociationWithTagsResponse extends OrganizationResponse {

    private List<HashTagResponse> tags;

    public AssociationWithTagsResponse(Association association) {
        super(association);
        this.tags = association.getTags().stream().map(HashTagResponse::new).collect(Collectors.toList());
    }
}
