package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookedDate is a Querydsl query type for BookedDate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookedDate extends EntityPathBase<BookedDate> {

    private static final long serialVersionUID = -26365782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookedDate bookedDate1 = new QBookedDate("bookedDate1");

    public final DatePath<java.time.LocalDate> bookedDate = createDate("bookedDate", java.time.LocalDate.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QReservation Reservation;

    public QBookedDate(String variable) {
        this(BookedDate.class, forVariable(variable), INITS);
    }

    public QBookedDate(Path<? extends BookedDate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookedDate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookedDate(PathMetadata metadata, PathInits inits) {
        this(BookedDate.class, metadata, inits);
    }

    public QBookedDate(Class<? extends BookedDate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.Reservation = inits.isInitialized("Reservation") ? new QReservation(forProperty("Reservation"), inits.get("Reservation")) : null;
    }

}

