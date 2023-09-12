package com.web.Bang.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.HouseDto;
import com.web.Bang.dto.queryDslDto.QHouseDto;
import com.web.Bang.model.QHouse;
import com.web.Bang.model.QReservation;
import com.web.Bang.model.QUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class HouseRepositoryImpl implements HouseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HouseRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<List<HouseDto>> findAllByAddress(String address, int houseId) {
        List<HouseDto> result = queryFactory
                .select(new QHouseDto(
                        QHouse.house.id,
                        QHouse.house.name,
                        QHouse.house.address,
                        QHouse.house.detailAddress,
                        QHouse.house.hostId,
                        QHouse.house.starScore,
                        QHouse.house.oneDayPrice,
                        QHouse.house.type,
                        QHouse.house.image,
                        QHouse.house.capacity,
                        QHouse.house.infoText,
                        QHouse.house.creationDate
                ))
                .from(QHouse.house)
                .where(QHouse.house.address.contains(address), QHouse.house.id.eq(houseId))
                .orderBy(QHouse.house.starScore.desc())
                .limit(4)
                .fetch();

        return Optional.ofNullable(result);
    }

    @Override
    public List<HouseDto> findAllByHostId(int hostId) {
        return queryFactory
                .select(new QHouseDto(
                        QHouse.house.id,
                        QHouse.house.name,
                        QHouse.house.address,
                        QHouse.house.detailAddress,
                        QHouse.house.hostId,
                        QHouse.house.starScore,
                        QHouse.house.oneDayPrice,
                        QHouse.house.type,
                        QHouse.house.image,
                        QHouse.house.capacity,
                        QHouse.house.infoText,
                        QHouse.house.creationDate
                ))
                .from(QHouse.house)
                .where(QHouse.house.hostId.id.eq(hostId))
                .orderBy(QHouse.house.id.desc())
                .fetch();
    }

    @Override
    public List<HouseDto> findAllByAddressAndTypeOrderByIdDesc(String address, String type) {
        return queryFactory
                .select(new QHouseDto(
                        QHouse.house.id,
                        QHouse.house.name,
                        QHouse.house.address,
                        QHouse.house.detailAddress,
                        QHouse.house.hostId,
                        QHouse.house.starScore,
                        QHouse.house.oneDayPrice,
                        QHouse.house.type,
                        QHouse.house.image,
                        QHouse.house.capacity,
                        QHouse.house.infoText,
                        QHouse.house.creationDate
                ))
                .from(QHouse.house)
                .where(QHouse.house.address.contains(address).and(QHouse.house.type.contains(type)))
                .orderBy(QHouse.house.id.desc())
                .fetch();
    }

    @Override
    public List<HouseDto> findAllByAddressOrTypeOrderByIdDesc(String address, String type) {
        return queryFactory
                .select(new QHouseDto(
                        QHouse.house.id,
                        QHouse.house.name,
                        QHouse.house.address,
                        QHouse.house.detailAddress,
                        QHouse.house.hostId,
                        QHouse.house.starScore,
                        QHouse.house.oneDayPrice,
                        QHouse.house.type,
                        QHouse.house.image,
                        QHouse.house.capacity,
                        QHouse.house.infoText,
                        QHouse.house.creationDate
                ))
                .from(QHouse.house)
                .where(QHouse.house.address.contains(address).or(QHouse.house.type.contains(type)))
                .orderBy(QHouse.house.id.desc())
                .fetch();
    }

    @Override
    public List<HouseDto> findAllByStarScore() {
        return queryFactory
                .select(new QHouseDto(
                        QHouse.house.id,
                        QHouse.house.name,
                        QHouse.house.address,
                        QHouse.house.detailAddress,
                        QHouse.house.hostId,
                        QHouse.house.starScore,
                        QHouse.house.oneDayPrice,
                        QHouse.house.type,
                        QHouse.house.image,
                        QHouse.house.capacity,
                        QHouse.house.infoText,
                        QHouse.house.creationDate
                ))
                .from(QHouse.house)
                .orderBy(QHouse.house.starScore.desc())
                .limit(3)
                .fetch();
    }

    @Override
    public List<HouseDto> findAllHouse() {
        return queryFactory
                .select(new QHouseDto(
                        QHouse.house.id,
                        QHouse.house.name,
                        QHouse.house.address,
                        QHouse.house.detailAddress,
                        QHouse.house.hostId,
                        QHouse.house.starScore,
                        QHouse.house.oneDayPrice,
                        QHouse.house.type,
                        QHouse.house.image,
                        QHouse.house.capacity,
                        QHouse.house.infoText,
                        QHouse.house.creationDate
                ))
                .from(QHouse.house)
                .orderBy(QHouse.house.id.desc())
                .fetch();
    }

    @Override
    public List<AdmintableDto> findByMonthBestHouse(String month, int limit) {
        String cMonth = month.length() == 1 ? "0" + month : month;
        return queryFactory
                .select(Projections.constructor(AdmintableDto.class,
                        QHouse.house.name.concat("(").concat(QUser.user.username).concat(")"),
                        QReservation.reservation.id.count().castToNum(Integer.class)))
                .from(QHouse.house)
                .innerJoin(QReservation.reservation)
                .on(QHouse.house.id.eq(QReservation.reservation.houseId.id))
                .innerJoin(QUser.user)
                .on(QHouse.house.hostId.id.eq(QUser.user.id))
                .where(QReservation.reservation.checkInDate.month().eq(Integer.parseInt(cMonth)))
                .groupBy(QHouse.house.id)
                .orderBy(QReservation.reservation.id.count().desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<AdmintableDto> loadHouseMonthTableCount() {
        return queryFactory
                .select(Projections.constructor(AdmintableDto.class,
                        QHouse.house.creationDate.month().castToNum(Integer.class),
                        QHouse.house.id.count().castToNum(Integer.class)))
                .from(QHouse.house)
                .groupBy(QHouse.house.creationDate.month())
                .orderBy(QHouse.house.creationDate.month().asc())
                .fetch();
    }

    @Override
    public List<AdmintableDto> loadAddressHouseCount() {
        return queryFactory
                .select(Projections.constructor(AdmintableDto.class,
                        QHouse.house.address,
                        QHouse.house.address.count().castToNum(Integer.class)))
                .from(QHouse.house)
                .groupBy(QHouse.house.address)
                .orderBy(QHouse.house.address.count().desc())
                .fetch();
    }
}
