package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QReservationDto is a Querydsl Projection type for ReservationDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReservationDto extends ConstructorExpression<ReservationDto> {

    private static final long serialVersionUID = -1614339595L;

    public QReservationDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> guestId, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> hostId, com.querydsl.core.types.Expression<? extends com.web.Bang.model.House> houseId, com.querydsl.core.types.Expression<? extends java.sql.Date> checkInDate, com.querydsl.core.types.Expression<? extends java.sql.Date> checkOutDate, com.querydsl.core.types.Expression<Integer> headCount, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<? extends java.sql.Timestamp> creationDate, com.querydsl.core.types.Expression<String> request, com.querydsl.core.types.Expression<com.web.Bang.model.type.ReservationType> approvalStatus) {
        super(ReservationDto.class, new Class<?>[]{int.class, com.web.Bang.model.User.class, com.web.Bang.model.User.class, com.web.Bang.model.House.class, java.sql.Date.class, java.sql.Date.class, int.class, int.class, java.sql.Timestamp.class, String.class, com.web.Bang.model.type.ReservationType.class}, id, guestId, hostId, houseId, checkInDate, checkOutDate, headCount, price, creationDate, request, approvalStatus);
    }

}

