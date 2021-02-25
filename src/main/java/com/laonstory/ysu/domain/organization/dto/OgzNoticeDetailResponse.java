package com.laonstory.ysu.domain.organization.dto;

import com.laonstory.ysu.domain.common.dto.FileResponse;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.OgzNoticeFile;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OgzNoticeDetailResponse extends OgzNoticeResponse{


    private List<FileResponse> files;
    private int commentCount;
    private Boolean hasLike;

    // TODO: 스크랩 했는 지 여부 hasScrap 추가하기

    public OgzNoticeDetailResponse(OgzNotice notice, User user) {
        super(notice);
        this.files = notice.getFiles().stream().map(FileResponse::new).collect(Collectors.toList());
        this.commentCount = notice.getComments().size();
        this.hasLike = notice.getLikes().stream().anyMatch(e -> e.getUser().getId().equals(user.getId()));
    }
}
