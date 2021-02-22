package com.laonstory.ysu.domain.major.domain;

import com.laonstory.ysu.domain.college.domain.College;
import com.laonstory.ysu.domain.common.SNS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_MAJOR")
public class Major extends SNS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String profile;
    private String bgImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id")
    private College college;

}
