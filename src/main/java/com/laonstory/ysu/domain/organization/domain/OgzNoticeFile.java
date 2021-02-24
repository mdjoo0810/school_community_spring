package com.laonstory.ysu.domain.organization.domain;

import com.laonstory.ysu.domain.common.File;
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
@Table(name = "T_OGZ_NOTICE_FILE")
public class OgzNoticeFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    private OgzNotice notice;


}
