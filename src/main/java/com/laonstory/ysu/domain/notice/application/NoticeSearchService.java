package com.laonstory.ysu.domain.notice.application;

import com.laonstory.ysu.domain.notice.domain.Notice;
import com.laonstory.ysu.domain.notice.dto.NoticeResponse;
import com.laonstory.ysu.domain.notice.persistence.NoticeRepositorySupport;
import com.laonstory.ysu.global.common.request.PageRequest;
import com.laonstory.ysu.global.common.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeSearchService {

    private final NoticeRepositorySupport noticeRepositorySupport;

    @Transactional
    public NoticeResponse findById(Long noticeId) {
        Notice notice = noticeRepositorySupport.findById(noticeId);
        notice.addViews();
        return new NoticeResponse(notice);
    }

    public PagingResponse<NoticeResponse> findAll(int page) {
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<Notice> notices = noticeRepositorySupport.findAll(pageRequest.of());

        List<NoticeResponse> responses = notices.getContent().stream().map(NoticeResponse::new).collect(Collectors.toList());

        return new PagingResponse<>(page, notices.getTotalPages(), notices.getTotalElements(),responses);
    }

}
