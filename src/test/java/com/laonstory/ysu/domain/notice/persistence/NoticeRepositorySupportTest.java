package com.laonstory.ysu.domain.notice.persistence;

import com.laonstory.ysu.domain.notice.domain.Notice;
import com.laonstory.ysu.domain.notice.domain.NoticeBuilder;
import com.laonstory.ysu.test.RepositoryTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NoticeRepositorySupportTest extends RepositoryTest {

    @Autowired
    private NoticeJpaRepository noticeJpaRepository;

    private Notice saveNotice;

    @Before
    public void setUp() throws Exception {
        saveNotice = noticeJpaRepository.save(NoticeBuilder.build());
    }

    @Test
    public void findByIdTest() {
        final Notice notice = noticeJpaRepository.findById(saveNotice.getId()).orElseThrow();

        assertThat(notice.getTitle(), is(equalTo(saveNotice.getTitle())));
        assertThat(notice.getContent(), is(equalTo(saveNotice.getContent())));
    }

}