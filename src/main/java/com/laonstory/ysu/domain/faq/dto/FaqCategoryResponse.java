package com.laonstory.ysu.domain.faq.dto;

import com.laonstory.ysu.domain.faq.domain.FaqCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FaqCategoryResponse {

    private Long id;
    private String title;
    private String description;

    public FaqCategoryResponse(FaqCategory category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.description = category.getDescription();
    }
}
