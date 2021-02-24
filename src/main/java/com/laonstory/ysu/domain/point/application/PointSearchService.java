package com.laonstory.ysu.domain.point.application;

import com.laonstory.ysu.domain.point.domain.PointHistory;
import com.laonstory.ysu.domain.point.dto.PointHistoryResponse;
import com.laonstory.ysu.domain.point.persistence.PointHistoryRepositorySupport;
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
public class PointSearchService {

    private final PointHistoryRepositorySupport pointHistoryRepositorySupport;

    /**
     * 유저의 포인트 기록 가져오기 (페이징)
     * @param user : token 으로 가져온 유저 정보
     * @param page : 원하는 페이지
     * @return PagingResponse
     */
    public PagingResponse<PointHistoryResponse> findByUserId(User user, int page) {

        PageRequest pageRequest = new PageRequest(page, 10);

        Page<PointHistory> pointHistories = pointHistoryRepositorySupport.findAllByUserId(user.getId(), pageRequest.of());

        List<PointHistoryResponse> responses = pointHistories.getContent().stream().map(PointHistoryResponse::new).collect(Collectors.toList());

        return new PagingResponse<>(page, pointHistories.getTotalPages(), pointHistories.getTotalElements(),responses);
    }

}
