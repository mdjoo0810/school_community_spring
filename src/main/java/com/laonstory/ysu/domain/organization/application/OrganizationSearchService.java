package com.laonstory.ysu.domain.organization.application;

import com.laonstory.ysu.domain.comment.dto.CommentWithChildrenResponse;
import com.laonstory.ysu.domain.organization.domain.OgzNotice;
import com.laonstory.ysu.domain.organization.domain.OgzNoticeComment;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeDetailResponse;
import com.laonstory.ysu.domain.organization.dto.OgzNoticeResponse;
import com.laonstory.ysu.domain.organization.model.OgzNoticeSearchModel;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeCommentRepositorySupport;
import com.laonstory.ysu.domain.organization.persistence.OgzNoticeRepositorySupport;
import com.laonstory.ysu.domain.user.domain.User;
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
public class OrganizationSearchService {

    private final OgzNoticeRepositorySupport ogzNoticeRepositorySupport;
    private final OgzNoticeCommentRepositorySupport ogzNoticeCommentRepositorySupport;

    public PagingResponse<OgzNoticeResponse> searchNotice(OgzNoticeSearchModel model, int page) {
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<OgzNotice> notices = ogzNoticeRepositorySupport.search(model, pageRequest.of());

        List<OgzNoticeResponse> responses = notices.getContent().stream().map(OgzNoticeResponse::new).collect(Collectors.toList());

        return new PagingResponse<>(page, notices.getTotalPages(), notices.getTotalElements(), responses);
    }

    @Transactional
    public OgzNoticeDetailResponse findById(Long noticeId, User user) {
        OgzNotice byId = ogzNoticeRepositorySupport.findById(noticeId);

        byId.read();

        return new OgzNoticeDetailResponse(byId, user);
    }

    public PagingResponse<CommentWithChildrenResponse> findAllCommentByNoticeId(User user, Long noticeId, int page){
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<OgzNoticeComment> comments = ogzNoticeCommentRepositorySupport.findAllByNoticeId(noticeId, pageRequest.of());

        List<CommentWithChildrenResponse> responses = comments.getContent()
                .stream().map(e -> new CommentWithChildrenResponse(e, user)).collect(Collectors.toList());

        return new PagingResponse<>(page, comments.getTotalPages(), comments.getTotalElements(), responses);
    }
}
