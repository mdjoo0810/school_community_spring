package com.laonstory.ysu.domain.point.dto;

import com.laonstory.ysu.domain.point.domain.PointHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistoryResponse {

    private Long id;
    private Long amount;
    private String description;
    private LocalDateTime createdAt;

    public PointHistoryResponse(final PointHistory history) {
        this.id = history.getId();
        this.amount = history.getAmount();
        this.description = history.getDescription();
        this.createdAt = history.getCreatedAt();
    }

}
