package com.laonstory.ysu.domain.organization.domain;

import com.laonstory.ysu.domain.user.domain.User;
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
@Table(name = "T_OGZ_NOTICE_LIKE")
public class OgzNoticeLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OgzNotice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
