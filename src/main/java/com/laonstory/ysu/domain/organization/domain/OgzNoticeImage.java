package com.laonstory.ysu.domain.organization.domain;

import com.laonstory.ysu.domain.common.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_OGZ_NOTICE_IMAGE")
public class OgzNoticeImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private OgzNotice notice;

}
