package com.laonstory.ysu.domain.common.dto;


import com.laonstory.ysu.domain.common.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ImageResponse {
    private Long id;
    private String path;

    public ImageResponse (Image image) {
        this.id = image.getId();
        this.path = image.getPath();
    }
}
