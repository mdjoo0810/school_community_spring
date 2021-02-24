package com.laonstory.ysu.domain.notice.domain;

import static org.junit.jupiter.api.Assertions.*;

public class NoticeBuilder {

    public static Notice build() {
        final String title = "테스트 제목";
        final String content = "테스트 내용";

        return createNotice(title, content);
    }

    public static Notice build(String title, String content) {
        return createNotice(title, content);
    }

    private static Notice createNotice(String title, String content) {
        return Notice.builder()
                .title(title)
                .content(content)
                .views(0L)
                .build();
    }

}