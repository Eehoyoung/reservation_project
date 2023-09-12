package com.web.Bang.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.HouseWaitDto;
import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.HostTableDto;
import com.web.Bang.dto.queryDslDto.QHostTableDto;
import com.web.Bang.dto.queryDslDto.QReservationDto;
import com.web.Bang.dto.queryDslDto.ReservationDto;
import com.web.Bang.model.QHouse;
import com.web.Bang.model.QReservation;
import com.web.Bang.model.QUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReservationRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReservationDto> findByGuestId(int guestId) {
        return queryFactory
                .select(new QReservationDto(
                        QReservation.reservation.id,
                        QReservation.reservation.guestId,
                        QReservation.reservation.hostId,
                        QReservation.reservation.houseId,
                        QReservation.reservation.checkInDate,
                        QReservation.reservation.checkOutDate,
                        QReservation.reservation.headCount,
                        QReservation.reservation.price,
                        QReservation.reservation.creationDate,
                        QReservation.reservation.request,
                        QReservation.reservation.approvalStatus
                ))
                .from(QReservation.reservation)
                .where(QReservation.reservation.guestId.id.eq(guestId))
                .orderBy(QReservation.reservation.id.desc())
                .fetch();
    }

    @Override
    public List<ReservationDto> findByHostId(int id) {
        return queryFactory
                .select(new QReservationDto(
                        QReservation.reservation.id,
                        QReservation.reservation.guestId,
                        QReservation.reservation.hostId,
                        QReservation.reservation.houseId,
                        QReservation.reservation.checkInDate,
                        QReservation.reservation.checkOutDate,
                        QReservation.reservation.headCount,
                        QReservation.reservation.price,
                        QReservation.reservation.creationDate,
                        QReservation.reservation.request,
                        QReservation.reservation.approvalStatus
                ))
                .from(QReservation.reservation)
                .where(QReservation.reservation.hostId.id.eq(id))
                .orderBy(QReservation.reservation.id.desc())
                .fetch();
    }

    @Override
    public List<AdmintableDto> loadReservationMonthTableCount() {
        return queryFactory
                .select(Projections.constructor(AdmintableDto.class,
                        QReservation.reservation.creationDate.month().castToNum(Integer.class),
                        QReservation.reservation.id.count().castToNum(Integer.class)))
                .from(QReservation.reservation)
                .groupBy(QReservation.reservation.creationDate.month())
                .orderBy(QReservation.reservation.creationDate.month().asc())
                .fetch();
    }

    @Override
    public List<HostTableDto> getlist(int hostId, int houseId, int month) {
        return queryFactory
                .select(new QHostTableDto(
                        QReservation.reservation.id.as("id"),
                        QReservation.reservation.guestId.username,
                        QReservation.reservation.headCount,
                        QReservation.reservation.checkInDate,
                        QReservation.reservation.checkOutDate,
                        QReservation.reservation.price,
                        QReservation.reservation.guestId.phoneNumber,
                        QReservation.reservation.request,
                        QReservation.reservation.approvalStatus.stringValue(),
                        QReservation.reservation.houseId.id.as("houseId"),
                        QReservation.reservation.houseId.name.as("houseName")
                ))
                .from(QReservation.reservation)
                .innerJoin(QHouse.house)
                .on(QReservation.reservation.houseId.id.eq(QHouse.house.id))
                .innerJoin(QUser.user)
                .on(QReservation.reservation.guestId.id.eq(QUser.user.id))
                .where(QReservation.reservation.hostId.id.eq(hostId).and(QReservation.reservation.checkInDate.month().eq(month).and(QReservation.reservation.hostId.id.eq(houseId))))
                .orderBy(QReservation.reservation.id.desc())
                .limit(31)
                .fetch();

    }

    @Override
    public List<HostTableDto> getlist(int hostId, int month) {
        return queryFactory
                .select(new QHostTableDto(
                        QReservation.reservation.id.as("id"),
                        QReservation.reservation.guestId.username,
                        QReservation.reservation.headCount,
                        QReservation.reservation.checkInDate,
                        QReservation.reservation.checkOutDate,
                        QReservation.reservation.guestId.phoneNumber,
                        QReservation.reservation.request,
                        QReservation.reservation.approvalStatus.stringValue(),
                        QReservation.reservation.houseId.id.as("houseId"),
                        QReservation.reservation.houseId.name.as("houseName")
                ))
                .from(QReservation.reservation)
                .innerJoin(QHouse.house)
                .on(QReservation.reservation.houseId.id.eq(QHouse.house.id))
                .innerJoin(QUser.user)
                .on(QReservation.reservation.guestId.id.eq(QUser.user.id))
                .where(QReservation.reservation.hostId.id.eq(hostId).and(QReservation.reservation.checkInDate.month().eq(month)))
                .orderBy(QReservation.reservation.id.desc())
                .fetch();
    }

    @Override
    public List<HouseWaitDto> getWaitCount(int id) {
        return queryFactory
                .select(Projections.constructor(HouseWaitDto.class,
                        QReservation.reservation.id.count().castToNum(Integer.class).as("wait"),
                        QReservation.reservation.houseId.id
                ))
                .from(QReservation.reservation)
                .where(QReservation.reservation.hostId.id.eq(id)
                        .and(QReservation.reservation.approvalStatus.stringValue().like("WAITING")))
                .groupBy(QReservation.reservation.houseId)
                .fetch();
    }
}
