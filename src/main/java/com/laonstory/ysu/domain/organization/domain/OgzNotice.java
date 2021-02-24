package com.laonstory.ysu.domain.organization.domain;

import com.laonstory.ysu.domain.common.BaseTimeEntity;
import com.laonstory.ysu.domain.organization.domain.Organization;
import com.laonstory.ysu.domain.organization.model.OgzNoticeMenu;
import com.laonstory.ysu.domain.organization.model.OgzTabType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_OGZ_NOTICE")
public class OgzNotice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OgzTabType tabType;

    @Enumerated(value = EnumType.STRING)
    private OgzNoticeMenu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    private String title;
    private String content;
    private String url;
    private Long views;


    private Boolean isFix;
    private Boolean isWhole;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OgzNoticeComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OgzNoticeTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OgzNoticeFile> files = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OgzNoticeImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OgzNoticeLike> likes = new ArrayList<>();
}
