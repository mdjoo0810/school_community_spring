package com.laonstory.ysu.domain.association.domain;

import com.laonstory.ysu.domain.major.domain.Major;
import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_ASSOCIATION")
public class Association extends Organization {

    @ManyToOne(fetch = FetchType.LAZY)
    private Major major;

    @OneToMany(mappedBy = "association", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AssociationTag> tags = new ArrayList<>();

}
