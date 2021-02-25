package com.laonstory.ysu.domain.club.domain;

import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "T_CLUB")
public class Club extends Organization {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubCategory category;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ClubTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ClubAlbum> albums = new ArrayList<>();
}
