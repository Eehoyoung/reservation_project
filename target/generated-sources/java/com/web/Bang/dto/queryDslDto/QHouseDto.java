package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QHouseDto is a Querydsl Projection type for HouseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QHouseDto extends ConstructorExpression<HouseDto> {

    private static final long serialVersionUID = 519911329L;

    public QHouseDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<String> detailAddress, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> hostId, com.querydsl.core.types.Expression<Double> starScore, com.querydsl.core.types.Expression<Integer> oneDayPrice, com.querydsl.core.types.Expression<String> type, com.querydsl.core.types.Expression<? extends com.web.Bang.model.Image> image, com.querydsl.core.types.Expression<Integer> capacity, com.querydsl.core.types.Expression<String> infoText, com.querydsl.core.types.Expression<? extends java.sql.Timestamp> creationDate) {
        super(HouseDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, com.web.Bang.model.User.class, double.class, int.class, String.class, com.web.Bang.model.Image.class, int.class, String.class, java.sql.Timestamp.class}, id, name, address, detailAddress, hostId, starScore, oneDayPrice, type, image, capacity, infoText, creationDate);
    }

}

