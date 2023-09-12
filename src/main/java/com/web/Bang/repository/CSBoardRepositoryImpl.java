package com.web.Bang.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.queryDslDto.CustomServiceBoardDto;
import com.web.Bang.dto.queryDslDto.QCustomServiceBoardDto;
import com.web.Bang.model.QCustomServiceBoard;
import com.web.Bang.model.type.CSBoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CSBoardRepositoryImpl implements CSBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CSBoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<CustomServiceBoardDto> findByTitleContaining(String title, Pageable pageable) {
        List<CustomServiceBoardDto> content = queryFactory
                .select(new QCustomServiceBoardDto(
                        QCustomServiceBoard.customServiceBoard.id,
                        QCustomServiceBoard.customServiceBoard.user,
                        QCustomServiceBoard.customServiceBoard.title,
                        QCustomServiceBoard.customServiceBoard.boardType,
                        QCustomServiceBoard.customServiceBoard.count,
                        QCustomServiceBoard.customServiceBoard.content,
                        QCustomServiceBoard.customServiceBoard.createTime,
                        QCustomServiceBoard.customServiceBoard.secret
                ))
                .from(QCustomServiceBoard.customServiceBoard)
                .where(QCustomServiceBoard.customServiceBoard.title.contains(title), QCustomServiceBoard.customServiceBoard.boardType.eq(CSBoardType.NORMAL))
                .orderBy(QCustomServiceBoard.customServiceBoard.id.desc())
                .offset(pageable.getOffset()) // offset
                .limit(pageable.getPageSize()) // limit
                .fetch();

        int totalSize = queryFactory // count 쿼리
                .selectFrom(QCustomServiceBoard.customServiceBoard)
                .where(QCustomServiceBoard.customServiceBoard.title.contains(title))
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    @Override
    public List<CustomServiceBoardDto> loadNoticeBoard() {
        return queryFactory
                .select(new QCustomServiceBoardDto(
                        QCustomServiceBoard.customServiceBoard.id,
                        QCustomServiceBoard.customServiceBoard.user,
                        QCustomServiceBoard.customServiceBoard.title,
                        QCustomServiceBoard.customServiceBoard.boardType,
                        QCustomServiceBoard.customServiceBoard.count,
                        QCustomServiceBoard.customServiceBoard.content,
                        QCustomServiceBoard.customServiceBoard.createTime,
                        QCustomServiceBoard.customServiceBoard.secret
                ))
                .from(QCustomServiceBoard.customServiceBoard)
                .where(QCustomServiceBoard.customServiceBoard.boardType.eq(CSBoardType.NOTICE))
                .orderBy(QCustomServiceBoard.customServiceBoard.id.desc())
                .fetch();
    }
}
