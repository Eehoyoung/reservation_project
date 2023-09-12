package com.web.Bang.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.queryDslDto.BookedDateDto;
import com.web.Bang.dto.queryDslDto.QBookedDateDto;
import com.web.Bang.model.QBookedDate;
import com.web.Bang.model.QReservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookedDateRepositoryImpl implements BookedDateRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BookedDateRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BookedDateDto> findAllByHouseId(int houseId) {
        return queryFactory
                .select(new QBookedDateDto(
                        QBookedDate.bookedDate1.id,
                        QBookedDate.bookedDate1.Reservation.id,
                        QBookedDate.bookedDate1.bookedDate
                ))
                .from(QBookedDate.bookedDate1)
                .innerJoin(QReservation.reservation).on(QBookedDate.bookedDate1.Reservation.id.eq(QReservation.reservation.id))
                .where(QBookedDate.bookedDate1.Reservation.houseId.id.eq(houseId))
                .fetch();
    }
}
