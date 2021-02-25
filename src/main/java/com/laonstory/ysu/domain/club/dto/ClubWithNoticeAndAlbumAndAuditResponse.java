package com.laonstory.ysu.domain.club.dto;

import com.laonstory.ysu.domain.club.domain.Club;
import com.laonstory.ysu.domain.club.domain.ClubAlbum;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ClubWithNoticeAndAlbumAndAuditResponse {
    private ClubResponse club;
    private List<OgzNoticeResponse> notices;
    private List<ClubAlbumResponse> albums;
    private List<OgzNoticeResponse> audits;

    public ClubWithNoticeAndAlbumAndAuditResponse(Club club,
                                                  List<OgzNotice> notices,
                                                  List<ClubAlbum> albums,
                                                  List<OgzNotice> audits) {
        this.club = new ClubResponse(club);
        this.notices = notices.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
        this.albums = albums.stream().map(ClubAlbumResponse::new).collect(Collectors.toList());
        this.audits = audits.stream().map(OgzNoticeResponse::new).collect(Collectors.toList());
    }
}
