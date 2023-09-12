package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QCustomServiceBoardDto is a Querydsl Projection type for CustomServiceBoardDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCustomServiceBoardDto extends ConstructorExpression<CustomServiceBoardDto> {

    private static final long serialVersionUID = -44876421L;

    public QCustomServiceBoardDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> user, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<com.web.Bang.model.type.CSBoardType> csBoardType, com.querydsl.core.types.Expression<Integer> count, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<? extends java.sql.Timestamp> createTime, com.querydsl.core.types.Expression<Integer> secret) {
        super(CustomServiceBoardDto.class, new Class<?>[]{int.class, com.web.Bang.model.User.class, String.class, com.web.Bang.model.type.CSBoardType.class, int.class, String.class, java.sql.Timestamp.class, int.class}, id, user, title, csBoardType, count, content, createTime, secret);
    }

}

