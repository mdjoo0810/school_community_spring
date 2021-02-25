package com.laonstory.ysu.domain.club.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubSearchModel {

    private Long categoryId;
    private String query;

}
