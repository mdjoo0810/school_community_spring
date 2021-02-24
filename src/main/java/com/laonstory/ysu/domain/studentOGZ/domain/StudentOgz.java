package com.laonstory.ysu.domain.studentOGZ.domain;

import com.laonstory.ysu.domain.organization.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_STUDENT_OGZ")
public class StudentOgz extends Organization {

    public enum OgzType {
        COUNCIL,
        SASAENG,
        CHUNCHU,
        YMBS
    }

    @Enumerated(value = EnumType.STRING)
    private OgzType type;

}
