package com.laonstory.ysu.domain.major.dto;

import com.laonstory.ysu.domain.major.domain.Major;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MajorResponse {
    private Long id;

    private String title;
    private String description;
    private String profile;
    private String bgImage;

    public MajorResponse(final Major major) {
        this.id = major.getId();
        this.title = major.getTitle();
        this.description = major.getDescription();
        this.profile = major.getProfile();
        this.bgImage = major.getBgImage();
    }
}
