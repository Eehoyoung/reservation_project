package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QReviewDto is a Querydsl Projection type for ReviewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewDto extends ConstructorExpression<ReviewDto> {

    private static final long serialVersionUID = -1602841947L;

    public QReviewDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<? extends com.web.Bang.model.House> houseId, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> guestId, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Double> starScore, com.querydsl.core.types.Expression<? extends java.sql.Timestamp> creationDate, com.querydsl.core.types.Expression<? extends java.util.List<com.web.Bang.model.Reply>> replies) {
        super(ReviewDto.class, new Class<?>[]{int.class, com.web.Bang.model.House.class, com.web.Bang.model.User.class, String.class, double.class, java.sql.Timestamp.class, java.util.List.class}, id, houseId, guestId, content, starScore, creationDate, replies);
    }

    public QReviewDto(com.querydsl.core.types.Expression<? extends com.web.Bang.model.House> houseId, com.querydsl.core.types.Expression<Double> starScore) {
        super(ReviewDto.class, new Class<?>[]{com.web.Bang.model.House.class, double.class}, houseId, starScore);
    }

}

