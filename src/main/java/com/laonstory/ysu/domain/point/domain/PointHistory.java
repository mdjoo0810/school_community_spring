package com.laonstory.ysu.domain.point.domain;

import com.laonstory.ysu.domain.point.dto.PointHistoryRequest;
import com.laonstory.ysu.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_POINT_HISTORY")
public class PointHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;
    private String description;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 포인트 기록 생성
    public static PointHistory create(PointHistoryRequest dto, User user) {
        return PointHistory.builder()
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
    }

}
