package com.laonstory.ysu.domain.club.application;

import com.laonstory.ysu.domain.club.domain.Club;
import com.laonstory.ysu.domain.club.domain.ClubAlbum;
import com.laonstory.ysu.domain.club.dto.ClubResponse;
import com.laonstory.ysu.domain.club.dto.ClubWithNoticeAndAlbumAndAuditResponse;
import com.laonstory.ysu.domain.club.dto.ClubWithNoticeResponse;
import com.laonstory.ysu.domain.club.model.ClubSearchModel;
import com.laonstory.ysu.domain.club.persistence.ClubAlbumRepositorySupport;
import com.laonstory.ysu.domain.club.persistence.ClubRepositorySupport;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.dto.OrganizationWithNoticeAndActivityAndAuditResponse;
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
public class ClubSearchService {

    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;
    private final ClubRepositorySupport clubRepositorySupport;
    private final ClubAlbumRepositorySupport clubAlbumRepositorySupport;

    public Club findAssociation () {
        return clubRepositorySupport.findAssociation();
    }

    public ClubWithNoticeResponse findNoticesWithClubs(ClubSearchModel model) {

        List<OgzNotice> notices = ogzNoticeRepositorySupport
                .findLimit_3ByOgzNoticeSearchModelAndIsWholeTrue(
                        new OgzNoticeSearchModel(OgzTabType.CLUB, Collections.singletonList(OgzNoticeMenu.NOTICE)));

        List<Club> clubs = clubRepositorySupport
                .findLimit_3BySearchModel(model);

        return new ClubWithNoticeResponse(clubs, notices);

    }

    public OrganizationWithNoticeAndActivityAndAuditResponse findAssociation_NAAR() {

        Club club = clubRepositorySupport.findAssociation();

        List<OgzNotice> notices = ogzNoticeRepositorySupport
                .findLimit_3ByOgzNoticeSearchModel(
                        new OgzNoticeSearchModel(OgzTabType.CLUB, Collections.singletonList(OgzNoticeMenu.NOTICE), club.getId()));

        List<OgzNotice> activitiesAndAudits = ogzNoticeRepositorySupport
                .findLimit_3ByOgzNoticeSearchModel(
                        new OgzNoticeSearchModel(OgzTabType.CLUB, Arrays.asList(OgzNoticeMenu.ACTIVITY, OgzNoticeMenu.AUDIT), club.getId()));

        return new OrganizationWithNoticeAndActivityAndAuditResponse(club, notices, activitiesAndAudits);
    }

    public ClubWithNoticeAndAlbumAndAuditResponse find_NAAR(Long clubId) {
        Club club = clubRepositorySupport.findById(clubId);

        List<OgzNotice> notices = ogzNoticeRepositorySupport
                .findLimit_3ByOgzNoticeSearchModel(
                        new OgzNoticeSearchModel(OgzTabType.CLUB, Collections.singletonList(OgzNoticeMenu.NOTICE), club.getId()));

        List<ClubAlbum> albums = clubAlbumRepositorySupport.findLimit_3ByClubId(club.getId());

        List<OgzNotice> audits = ogzNoticeRepositorySupport
                .findLimit_3ByOgzNoticeSearchModelAndIsWholeTrue(
                        new OgzNoticeSearchModel(OgzTabType.CLUB, Collections.singletonList(OgzNoticeMenu.AUDIT), club.getId()));

        return new ClubWithNoticeAndAlbumAndAuditResponse(club, notices, albums, audits);
    }

    public PagingResponse<ClubResponse> search(ClubSearchModel model, int page) {
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<Club> clubs = clubRepositorySupport.search(model, pageRequest.of());

        List<ClubResponse> responses = clubs.getContent().stream().map(ClubResponse::new).collect(Collectors.toList());

        return new PagingResponse<>(page, clubs.getTotalPages(), clubs.getTotalElements(),responses);
    }

}
