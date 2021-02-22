package com.laonstory.ysu.domain.major.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMajor is a Querydsl query type for Major
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMajor extends EntityPathBase<Major> {

    private static final long serialVersionUID = 2072113825L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMajor major = new QMajor("major");

    public final com.laonstory.ysu.domain.common.QSNS _super = new com.laonstory.ysu.domain.common.QSNS(this);

    public final StringPath bgImage = createString("bgImage");

    public final com.laonstory.ysu.domain.college.domain.QCollege college;

    public final StringPath description = createString("description");

    //inherited
    public final StringPath facebook = _super.facebook;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath instagram = _super.instagram;

    //inherited
    public final StringPath naver = _super.naver;

    public final StringPath profile = createString("profile");

    public final StringPath title = createString("title");

    public QMajor(String variable) {
        this(Major.class, forVariable(variable), INITS);
    }

    public QMajor(Path<? extends Major> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMajor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMajor(PathMetadata metadata, PathInits inits) {
        this(Major.class, metadata, inits);
    }

    public QMajor(Class<? extends Major> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.college = inits.isInitialized("college") ? new com.laonstory.ysu.domain.college.domain.QCollege(forProperty("college")) : null;
    }

}

