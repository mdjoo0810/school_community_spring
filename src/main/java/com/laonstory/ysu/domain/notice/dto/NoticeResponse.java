package com.laonstory.ysu.domain.notice.dto;

import com.laonstory.ysu.domain.notice.domain.Notice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private Long views;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public NoticeResponse (Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.views = notice.getViews();
        this.createDate = notice.getCreatedDate();
        this.modifiedDate = notice.getModifiedDate();
    }

}
