package com.laonstory.ysu.domain.organization.dto;

import com.laonstory.ysu.domain.common.dto.ImageResponse;
import com.laonstory.ysu.domain.hashTag.dto.HashTagResponse;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OgzNoticeResponse {

    private Long id;
    private OgzTabType tabType;
    private OgzNoticeMenu menu;

    private String title;
    private String content;
    private String url;
    private Long views;

    private int likes;

    private Boolean isFix;
    private Boolean isWhole;

    private List<HashTagResponse> tags;
    private List<ImageResponse> images;

    public OgzNoticeResponse(OgzNotice notice) {
        this.id = notice.getId();
        this.tabType = notice.getTabType();
        this.menu = notice.getMenu();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.likes = notice.getLikes().size();
        this.url = notice.getUrl();
        this.views = notice.getViews();
        this.isFix = notice.getIsFix();
        this.isWhole = notice.getIsWhole();
        this.tags = notice.getTags().stream().map(HashTagResponse::new).collect(Collectors.toList());
        this.images = notice.getImages().stream().map(ImageResponse::new).collect(Collectors.toList());
    }

}
