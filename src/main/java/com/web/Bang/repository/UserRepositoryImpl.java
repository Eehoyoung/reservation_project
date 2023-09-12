package com.web.Bang.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.QUserDto;
import com.web.Bang.dto.queryDslDto.UserDto;
import com.web.Bang.model.QUser;
import com.web.Bang.model.type.RoleType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserDto> findByRoleAndUserName(String role, String name) {
        return queryFactory
                .select(new QUserDto(
                        QUser.user.id,
                        QUser.user.username,
                        QUser.user.password,
                        QUser.user.email,
                        QUser.user.phoneNumber,
                        QUser.user.creationDate,
                        QUser.user.role,
                        QUser.user.loginType,
                        QUser.user.reportCount
                ))
                .from(QUser.user)
                .where(QUser.user.role.eq(RoleType.valueOf(role)).and(QUser.user.username.eq(name)))
                .fetch();
    }

    @Override
    public List<UserDto> findByUsernameContaining(String name) {
        return queryFactory
                .select(new QUserDto(
                        QUser.user.id,
                        QUser.user.username,
                        QUser.user.password,
                        QUser.user.email,
                        QUser.user.phoneNumber,
                        QUser.user.creationDate,
                        QUser.user.role,
                        QUser.user.loginType,
                        QUser.user.reportCount
                ))
                .from(QUser.user)
                .where(QUser.user.username.eq(name))
                .fetch();
    }

    @Override
    public List<AdmintableDto> loadUserMonthTableCount() {
        return queryFactory
                .select(Projections.constructor(AdmintableDto.class,
                        QUser.user.creationDate.month().castToNum(Integer.class),
                        QUser.user.id.count().castToNum(Integer.class)))
                .from(QUser.user)
                .groupBy(QUser.user.creationDate.month())
                .orderBy(QUser.user.creationDate.month().asc())
                .fetch();
    }
}
