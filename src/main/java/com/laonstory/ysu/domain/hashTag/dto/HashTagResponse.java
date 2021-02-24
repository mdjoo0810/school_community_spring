package com.laonstory.ysu.domain.hashTag.dto;

import com.laonstory.ysu.domain.hashTag.domain.HashTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HashTagResponse {

    private Long id;
    private String name;

    public HashTagResponse(HashTag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

}
