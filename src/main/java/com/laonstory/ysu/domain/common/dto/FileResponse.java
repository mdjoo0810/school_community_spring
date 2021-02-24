package com.laonstory.ysu.domain.common.dto;

import com.laonstory.ysu.domain.common.File;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileResponse {

    private Long id;
    private String name;
    private String path;

    public FileResponse (File file) {
        this.id = file.getId();
        this.name = file.getName();
        this.path = file.getPath();
    }

}
