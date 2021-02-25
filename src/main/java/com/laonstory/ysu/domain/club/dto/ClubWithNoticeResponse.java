package com.laonstory.ysu.domain.club.dto;

import com.laonstory.ysu.domain.club.domain.Club;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ClubWithNoticeResponse {

    private List<OgzNoticeResponse> notices;
    private List<ClubResponse> clubs;

    public ClubWithNoticeResponse(List<Club> clubs, List<OgzNotice> notices) {
        this.clubs = clubs.stream().map(ClubResponse::new).collect(Collectors.toList());
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
    }

}
