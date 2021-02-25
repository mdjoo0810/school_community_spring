package com.laonstory.ysu.domain.club.dto;

import com.laonstory.ysu.domain.club.domain.ClubCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClubCategoryResponse {

    private Long id;
    private String title;

    public ClubCategoryResponse(ClubCategory category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }

}
