package com.laonstory.ysu.domain.college.domain;

import com.laonstory.ysu.domain.common.SNS;
import com.laonstory.ysu.domain.major.domain.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COLLEGE")
public class College extends SNS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String profile;
    private String bgImage;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Major> majors = new ArrayList<>();

}
