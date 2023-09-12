package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWishList is a Querydsl query type for WishList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWishList extends EntityPathBase<WishList> {

    private static final long serialVersionUID = 1852046969L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWishList wishList = new QWishList("wishList");

    public final QUser guest;

    public final NumberPath<Integer> guestId = createNumber("guestId", Integer.class);

    public final QHouse house;

    public final NumberPath<Integer> houseId = createNumber("houseId", Integer.class);

    public QWishList(String variable) {
        this(WishList.class, forVariable(variable), INITS);
    }

    public QWishList(Path<? extends WishList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWishList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWishList(PathMetadata metadata, PathInits inits) {
        this(WishList.class, metadata, inits);
    }

    public QWishList(Class<? extends WishList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guest = inits.isInitialized("guest") ? new QUser(forProperty("guest")) : null;
        this.house = inits.isInitialized("house") ? new QHouse(forProperty("house"), inits.get("house")) : null;
    }

}

