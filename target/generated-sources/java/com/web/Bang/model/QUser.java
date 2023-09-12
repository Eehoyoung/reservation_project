package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 579807839L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<com.web.Bang.model.type.LoginType> loginType = createEnum("loginType", com.web.Bang.model.type.LoginType.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> reportCount = createNumber("reportCount", Integer.class);

    public final EnumPath<com.web.Bang.model.type.RoleType> role = createEnum("role", com.web.Bang.model.type.RoleType.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

