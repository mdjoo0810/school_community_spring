package com.laonstory.ysu.domain.common;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class SNS {

    private String instagram;
    private String facebook;
    private String naver;

}
