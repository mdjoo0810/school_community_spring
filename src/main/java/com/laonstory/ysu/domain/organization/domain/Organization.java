package com.laonstory.ysu.domain.organization.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_ORGANIZATION")
public class Organization {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String profile;
    private String bgImage;
    private String instagram;
    private String facebook;
    private String naver;

}
