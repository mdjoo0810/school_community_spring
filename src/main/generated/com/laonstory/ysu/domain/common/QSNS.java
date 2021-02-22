package com.laonstory.ysu.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSNS is a Querydsl query type for SNS
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QSNS extends EntityPathBase<SNS> {

    private static final long serialVersionUID = -854779790L;

    public static final QSNS sNS = new QSNS("sNS");

    public final StringPath facebook = createString("facebook");

    public final StringPath instagram = createString("instagram");

    public final StringPath naver = createString("naver");

    public QSNS(String variable) {
        super(SNS.class, forVariable(variable));
    }

    public QSNS(Path<? extends SNS> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSNS(PathMetadata metadata) {
        super(SNS.class, metadata);
    }

}

