package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomServiceReply is a Querydsl query type for CustomServiceReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomServiceReply extends EntityPathBase<CustomServiceReply> {

    private static final long serialVersionUID = -932925734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomServiceReply customServiceReply = new QCustomServiceReply("customServiceReply");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final QCustomServiceBoard customServiceBoard;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCustomServiceReply(String variable) {
        this(CustomServiceReply.class, forVariable(variable), INITS);
    }

    public QCustomServiceReply(Path<? extends CustomServiceReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomServiceReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomServiceReply(PathMetadata metadata, PathInits inits) {
        this(CustomServiceReply.class, metadata, inits);
    }

    public QCustomServiceReply(Class<? extends CustomServiceReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customServiceBoard = inits.isInitialized("customServiceBoard") ? new QCustomServiceBoard(forProperty("customServiceBoard"), inits.get("customServiceBoard")) : null;
    }

}

