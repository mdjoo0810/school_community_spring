package com.laonstory.ysu.domain.club.domain;

import com.laonstory.ysu.domain.hashTag.domain.HashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_CLUB_TAG")
public class ClubTag extends HashTag {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

}
