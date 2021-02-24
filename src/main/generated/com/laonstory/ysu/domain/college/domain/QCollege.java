package com.laonstory.ysu.domain.college.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCollege is a Querydsl query type for College
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCollege extends EntityPathBase<College> {

    private static final long serialVersionUID = 1627028897L;

    public static final QCollege college = new QCollege("college");

    public final com.laonstory.ysu.domain.organization.domain.QOrganization _super = new com.laonstory.ysu.domain.organization.domain.QOrganization(this);

    //inherited
    public final StringPath bgImage = _super.bgImage;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final StringPath facebook = _super.facebook;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath instagram = _super.instagram;

    public final ListPath<com.laonstory.ysu.domain.major.domain.Major, com.laonstory.ysu.domain.major.domain.QMajor> majors = this.<com.laonstory.ysu.domain.major.domain.Major, com.laonstory.ysu.domain.major.domain.QMajor>createList("majors", com.laonstory.ysu.domain.major.domain.Major.class, com.laonstory.ysu.domain.major.domain.QMajor.class, PathInits.DIRECT2);

    //inherited
    public final StringPath naver = _super.naver;

    //inherited
    public final StringPath profile = _super.profile;

    //inherited
    public final StringPath title = _super.title;

    public QCollege(String variable) {
        super(College.class, forVariable(variable));
    }

    public QCollege(Path<? extends College> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCollege(PathMetadata metadata) {
        super(College.class, metadata);
    }

}

