package com.laonstory.ysu.domain.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "T_FILE")
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;
    private String name;

}
