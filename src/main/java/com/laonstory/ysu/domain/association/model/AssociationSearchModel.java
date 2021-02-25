package com.laonstory.ysu.domain.association.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssociationSearchModel {

    private Long collegeId;
    private Long majorId;
    private String query;

    public AssociationSearchModel (String query){
        this.query = query;
    }

    public AssociationSearchModel (Long collegeId, Long majorId) {
        this.collegeId = collegeId;
        this.majorId = majorId;
    }

}
