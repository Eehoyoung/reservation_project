package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;
import java.time.LocalDate;

/**
 * com.web.Bang.dto.queryDslDto.QBookedDateDto is a Querydsl Projection type for BookedDateDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBookedDateDto extends ConstructorExpression<BookedDateDto> {

    private static final long serialVersionUID = -313013977L;

    public QBookedDateDto(Expression<Integer> id, NumberPath<Integer> reservation, Expression<LocalDate> bookedDate) {
        super(BookedDateDto.class, new Class<?>[]{int.class, com.web.Bang.model.Reservation.class, java.time.LocalDate.class}, id, reservation, bookedDate);
    }

}

