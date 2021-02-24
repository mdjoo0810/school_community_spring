package com.laonstory.ysu.domain.college.domain;

import com.laonstory.ysu.domain.organization.domain.Organization;
import com.laonstory.ysu.domain.major.domain.Major;
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
@Table(name = "T_COLLEGE")
public class College extends Organization {

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Major> majors = new ArrayList<>();

}
