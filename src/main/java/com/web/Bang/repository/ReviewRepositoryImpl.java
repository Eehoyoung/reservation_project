package com.web.Bang.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.QReviewDto;
import com.web.Bang.dto.queryDslDto.ReviewDto;
import com.web.Bang.model.QReview;
import com.web.Bang.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Review> findAllByHouseId(int houseId, Pageable pageable) {
        List<Review> result = queryFactory
                .selectFrom(QReview.review)
                .leftJoin(QReview.review.replies).fetchJoin()
                .where(QReview.review.houseId.id.eq(houseId))
                .orderBy(QReview.review.id.desc())
                .offset(pageable.getOffset()) // offset
                .limit(pageable.getPageSize()) // limit
                .fetch();

        long totalSize = queryFactory // count 쿼리
                .selectFrom(QReview.review)
                .where(QReview.review.houseId.id.eq(houseId))
                .fetchCount();

        return new PageImpl<>(result, pageable, totalSize);
    }


    @Override
    public List<ReviewDto> findAllByHouseId(int houseId) {
        return queryFactory
                .select(new QReviewDto(
                        QReview.review.id,
                        QReview.review.houseId,
                        QReview.review.guestId,
                        QReview.review.content,
                        QReview.review.starScore,
                        QReview.review.creationDate,
                        QReview.review.replies
                ))
                .from(QReview.review)
                .where(QReview.review.houseId.id.eq(houseId))
                .orderBy(QReview.review.id.desc())
                .fetch();
    }

    @Override
    public Page<Review> findAllByGuestId(int guestId, Pageable pageable) {
        List<Review> result = queryFactory
                .selectFrom(QReview.review)
                .leftJoin(QReview.review.replies).fetchJoin()
                .where(QReview.review.guestId.id.eq(guestId))
                .orderBy(QReview.review.id.desc())
                .offset(pageable.getOffset()) // offset
                .limit(pageable.getPageSize()) // limit
                .fetch();

        long totalSize = queryFactory // count 쿼리
                .selectFrom(QReview.review)
                .where(QReview.review.guestId.id.eq(guestId))
                .fetchCount();

        return new PageImpl<>(result, pageable, totalSize);
    }

    @Override
    public List<AdmintableDto> loadReviewMonthTableCount() {
        return queryFactory
                .select(Projections.constructor(AdmintableDto.class,
                        QReview.review.creationDate.month().castToNum(Integer.class),
                        QReview.review.id.count().castToNum(Integer.class)))
                .from(QReview.review)
                .groupBy(QReview.review.creationDate.month())
                .orderBy(QReview.review.creationDate.month().asc())
                .fetch();
    }

    @Override
    public List<ReviewDto> getAvgStarScoreByHouse(int houseId) {
        return queryFactory
                .select(new QReviewDto(
                        QReview.review.houseId,
                        QReview.review.starScore.avg().round()
                ))
                .from(QReview.review)
                .where(QReview.review.houseId.id.eq(houseId))
                .groupBy(QReview.review.houseId)
                .fetch();
    }
}
