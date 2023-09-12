package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHouse is a Querydsl query type for House
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHouse extends EntityPathBase<House> {

    private static final long serialVersionUID = 782064396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHouse house = new QHouse("house");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final StringPath detailAddress = createString("detailAddress");

    public final QUser hostId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImage image;

    public final StringPath infoText = createString("infoText");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> oneDayPrice = createNumber("oneDayPrice", Integer.class);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final NumberPath<Double> starScore = createNumber("starScore", Double.class);

    public final StringPath type = createString("type");

    public QHouse(String variable) {
        this(House.class, forVariable(variable), INITS);
    }

    public QHouse(Path<? extends House> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHouse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHouse(PathMetadata metadata, PathInits inits) {
        this(House.class, metadata, inits);
    }

    public QHouse(Class<? extends House> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostId = inits.isInitialized("hostId") ? new QUser(forProperty("hostId")) : null;
        this.image = inits.isInitialized("image") ? new QImage(forProperty("image")) : null;
    }

}

