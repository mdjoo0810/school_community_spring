package com.laonstory.ysu.domain.club.dto;

import com.laonstory.ysu.domain.club.domain.ClubAlbum;
import com.laonstory.ysu.domain.common.dto.ImageResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ClubAlbumResponse {

    private Long id;

    private String title;
    private String description;
    private Long views;
    private List<ImageResponse> images;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ClubAlbumResponse(ClubAlbum album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.description = album.getDescription();
        this.views = album.getViews();
        this.images = album.getImages().stream().map(ImageResponse::new).collect(Collectors.toList());
        this.createdDate = album.getCreatedDate();
        this.modifiedDate = album.getModifiedDate();
    }

}
