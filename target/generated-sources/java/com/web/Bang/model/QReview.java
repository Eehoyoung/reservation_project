package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1248730900L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final QUser guestId;

    public final QHouse houseId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<Reply, QReply> replies = this.<Reply, QReply>createList("replies", Reply.class, QReply.class, PathInits.DIRECT2);

    public final NumberPath<Double> starScore = createNumber("starScore", Double.class);

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guestId = inits.isInitialized("guestId") ? new QUser(forProperty("guestId")) : null;
        this.houseId = inits.isInitialized("houseId") ? new QHouse(forProperty("houseId"), inits.get("houseId")) : null;
    }

}

