package com.laonstory.ysu.domain.club.dto;

import com.laonstory.ysu.domain.club.domain.Club;
import com.laonstory.ysu.domain.hashTag.dto.HashTagResponse;
import com.laonstory.ysu.domain.organization.domain.Organization;
import com.laonstory.ysu.domain.organization.dto.OrganizationResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ClubResponse extends OrganizationResponse {

    private List<HashTagResponse> tags;

    public ClubResponse(Club club) {
        super(club);
        this.tags = club.getTags().stream().map(HashTagResponse::new).collect(Collectors.toList());
    }
}
