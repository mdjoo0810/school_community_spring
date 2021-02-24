package com.laonstory.ysu.domain.faq.dto;

import com.laonstory.ysu.domain.faq.domain.Faq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FaqResponse {

    private Long id;
    private String title;
    private String description;

    public FaqResponse(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.description = faq.getDescription();
    }
}
