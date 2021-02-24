package com.laonstory.ysu.domain.notice.domain;

import com.laonstory.ysu.domain.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_NOTICE")
@Getter
@Builder
public class Notice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ColumnDefault("0")
    private Long views;

    public void addViews() {
        this.views += 1;
    }

}
