package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomServiceBoard is a Querydsl query type for CustomServiceBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomServiceBoard extends EntityPathBase<CustomServiceBoard> {

    private static final long serialVersionUID = -947418410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomServiceBoard customServiceBoard = new QCustomServiceBoard("customServiceBoard");

    public final EnumPath<com.web.Bang.model.type.CSBoardType> boardType = createEnum("boardType", com.web.Bang.model.type.CSBoardType.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createTime = createDateTime("createTime", java.sql.Timestamp.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<CustomServiceReply, QCustomServiceReply> replys = this.<CustomServiceReply, QCustomServiceReply>createList("replys", CustomServiceReply.class, QCustomServiceReply.class, PathInits.DIRECT2);

    public final NumberPath<Integer> secret = createNumber("secret", Integer.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public QCustomServiceBoard(String variable) {
        this(CustomServiceBoard.class, forVariable(variable), INITS);
    }

    public QCustomServiceBoard(Path<? extends CustomServiceBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomServiceBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomServiceBoard(PathMetadata metadata, PathInits inits) {
        this(CustomServiceBoard.class, metadata, inits);
    }

    public QCustomServiceBoard(Class<? extends CustomServiceBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

