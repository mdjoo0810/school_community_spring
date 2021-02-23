package com.laonstory.ysu.global.common.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
public final class PageRequest {

    private int page;
    private int size;
    // getter

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size);
    }
}