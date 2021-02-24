package com.laonstory.ysu.test.setup;

import com.laonstory.ysu.domain.notice.domain.Notice;
import com.laonstory.ysu.domain.notice.domain.NoticeBuilder;
import com.laonstory.ysu.domain.notice.persistence.NoticeJpaRepository;
import com.laonstory.ysu.test.config.TestProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Profile(TestProfile.TEST)
@Component
public class NoticeSetup {

    @Autowired
    private NoticeJpaRepository noticeJpaRepository;

    public Notice save() {
        final Notice notice = NoticeBuilder.build();
        return noticeJpaRepository.save(notice);
    }

    public List<Notice> save(int count) {
        List<Notice> notices = new ArrayList<>();
        IntStream.range(0, count).forEach(i -> notices.add(NoticeBuilder.build()));
        return noticeJpaRepository.saveAll(notices);
    }

}
