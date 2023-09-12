package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 1149843128L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final EnumPath<com.web.Bang.model.type.ReservationType> approvalStatus = createEnum("approvalStatus", com.web.Bang.model.type.ReservationType.class);

    public final DatePath<java.sql.Date> checkInDate = createDate("checkInDate", java.sql.Date.class);

    public final DatePath<java.sql.Date> checkOutDate = createDate("checkOutDate", java.sql.Date.class);

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final QUser guestId;

    public final NumberPath<Integer> headCount = createNumber("headCount", Integer.class);

    public final QUser hostId;

    public final QHouse houseId;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath request = createString("request");

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.guestId = inits.isInitialized("guestId") ? new QUser(forProperty("guestId")) : null;
        this.hostId = inits.isInitialized("hostId") ? new QUser(forProperty("hostId")) : null;
        this.houseId = inits.isInitialized("houseId") ? new QHouse(forProperty("houseId"), inits.get("houseId")) : null;
    }

}

