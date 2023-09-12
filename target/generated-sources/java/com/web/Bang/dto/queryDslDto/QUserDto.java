package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QUserDto is a Querydsl Projection type for UserDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserDto extends ConstructorExpression<UserDto> {

    private static final long serialVersionUID = -815260462L;

    public QUserDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<String> password, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> phoneNumber, com.querydsl.core.types.Expression<? extends java.sql.Timestamp> creationDate, com.querydsl.core.types.Expression<com.web.Bang.model.type.RoleType> role, com.querydsl.core.types.Expression<com.web.Bang.model.type.LoginType> loginType, com.querydsl.core.types.Expression<Integer> reportCount) {
        super(UserDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, java.sql.Timestamp.class, com.web.Bang.model.type.RoleType.class, com.web.Bang.model.type.LoginType.class, int.class}, id, username, password, email, phoneNumber, creationDate, role, loginType, reportCount);
    }

}

