package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QHostTableDto is a Querydsl Projection type for HostTableDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QHostTableDto extends ConstructorExpression<HostTableDto> {

    private static final long serialVersionUID = 226901243L;

    public QHostTableDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<Integer> headCount, com.querydsl.core.types.Expression<? extends java.sql.Date> checkInDate, com.querydsl.core.types.Expression<? extends java.sql.Date> checkOutDate, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> request, com.querydsl.core.types.Expression<String> approvalStatus, com.querydsl.core.types.Expression<Integer> houseId, com.querydsl.core.types.Expression<String> houseName) {
        super(HostTableDto.class, new Class<?>[]{int.class, String.class, int.class, java.sql.Date.class, java.sql.Date.class, int.class, String.class, String.class, String.class, int.class, String.class}, id, username, headCount, checkInDate, checkOutDate, price, phoneNumber, request, approvalStatus, houseId, houseName);
    }

    public QHostTableDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<Integer> headCount, com.querydsl.core.types.Expression<? extends java.sql.Date> checkInDate, com.querydsl.core.types.Expression<? extends java.sql.Date> checkOutDate, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<String> request, com.querydsl.core.types.Expression<String> approvalStatus, com.querydsl.core.types.Expression<Integer> houseId, com.querydsl.core.types.Expression<String> houseName) {
        super(HostTableDto.class, new Class<?>[]{int.class, String.class, int.class, java.sql.Date.class, java.sql.Date.class, String.class, String.class, String.class, int.class, String.class}, id, username, headCount, checkInDate, checkOutDate, phoneNumber, request, approvalStatus, houseId, houseName);
    }

    public QHostTableDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> houseId) {
        super(HostTableDto.class, new Class<?>[]{int.class, int.class}, id, houseId);
    }

}

