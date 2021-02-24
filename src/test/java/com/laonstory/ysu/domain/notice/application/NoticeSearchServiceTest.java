package com.laonstory.ysu.domain.notice.application;

import com.laonstory.ysu.domain.notice.domain.Notice;
import com.laonstory.ysu.domain.notice.domain.NoticeBuilder;
import com.laonstory.ysu.domain.notice.persistance.NoticeJpaRepository;
import com.laonstory.ysu.domain.notice.persistance.NoticeRepositorySupport;
import com.laonstory.ysu.test.MockTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

public class NoticeSearchServiceTest extends MockTest {

    @InjectMocks
    private NoticeSearchService noticeSearchService;

    @Mock
    private NoticeRepositorySupport noticeRepositorySupport;



    @Before
    public void setUp() {
    }

    @Test
    public void 공지사항_조회수_증가_3회(){
        // given
        final Notice notice = NoticeBuilder.build();
        given(noticeRepositorySupport.findById(anyLong())).willReturn(notice);

        // when
        noticeSearchService.findById(anyLong());
        noticeSearchService.findById(anyLong());
        noticeSearchService.findById(anyLong());

        // then
        assertThat(notice.getViews(), is(equalTo(3L)));
    }
}