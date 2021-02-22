package com.laonstory.ysu.domain.college.dto;

import com.laonstory.ysu.domain.college.domain.College;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollegeResponse {

    private Long id;

    private String title;
    private String description;
    private String profile;
    private String bgImage;

    public CollegeResponse(final College college) {
        this.id = college.getId();
        this.title = college.getTitle();
        this.description = college.getDescription();
        this.profile = college.getProfile();
        this.bgImage = college.getBgImage();
    }

}
