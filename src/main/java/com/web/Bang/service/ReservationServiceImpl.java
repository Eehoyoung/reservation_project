package com.web.Bang.service;

import com.web.Bang.dto.ApproveDto;
import com.web.Bang.dto.HostTableDto;
import com.web.Bang.dto.HouseWaitDto;
import com.web.Bang.model.*;
import com.web.Bang.model.type.ReservationType;
import com.web.Bang.repository.*;
import com.web.Bang.repository.queryStorage.HostTableQueryStorage;
import com.web.Bang.repository.queryStorage.QlrmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    // 리뷰 테스트 용
    public static int REVIEW_TEST = 0;

    private final HostTableQueryStorage queryStorage;

    private final QlrmRepository qlrmRepository;

    private final UserRepository userRepository;

    private final BookedDateRepository bookedDateRepository;

    private final ReservationRepository reservationRepository;

    private final HouseRepository houseRepository;

    @Transactional
    @Override
    public void makeReservation(Reservation reservation) {
        int[] tempIdList = reservation.getTempIdBox();
        House house = houseRepository.findById(tempIdList[2]).orElseThrow(() -> new RuntimeException("해당 숙소를 찾을 수 없습니다."));
        User guest = userRepository.findById(tempIdList[0]).orElseThrow(() -> new RuntimeException("해당 게스트를 찾을 수 없습니다."));
        User host = userRepository.findById(tempIdList[1]).orElseThrow(() -> new RuntimeException("해당 호스트를 찾을 수 없습니다."));
        reservation.setPrice(house.getOneDayPrice() * getRangeDay(reservation.getCheckInDate(), reservation.getCheckOutDate()));
        reservation.setHouseId(house);
        reservation.setGuestId(guest);
        reservation.setHostId(host);
        calculateBookedDates(reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation);
        reservation.setApprovalStatus(ReservationType.WAITING);
        reservationRepository.save(reservation);
    }

    private void calculateBookedDates(Date checkinDate, Date checkOutDate, Reservation res) {
        int range = getRangeDay(checkinDate, checkOutDate);

        for (int i = 0; i < range; i++) {
            BookedDate bookedDate = new BookedDate();
            bookedDate.setReservation(res);
            bookedDate.setBookedDate(changeToLocalDate(checkinDate).plusDays(i));
            bookedDateRepository.save(bookedDate);
        }

    }

    private LocalDate changeToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public int getRangeDay(Date checkinDate, Date checkOutDate) {
        long sec = (checkOutDate.getTime() - checkinDate.getTime()) / 1000;
        return ((int) sec / (24 * 60 * 60)) + REVIEW_TEST;
    }

    @Override
    @Modifying
    @Transactional
    public List<Reservation> getReservation(User user) {
        List<Reservation> reservation;
        reservation = reservationRepository.findByGuestId(user.getId());
        changeCompletedType(reservation);
        return reservation;
    }

    private void changeCompletedType(List<Reservation> listRes) {
        LocalDate nowtime = LocalDate.now();
        Date nowDate = Date.valueOf(nowtime);

        for (Reservation reservation : listRes) {
            if (reservation.getApprovalStatus() != ReservationType.PAID) {
                continue;
            }

            if (getRangeDay(reservation.getCheckOutDate(), nowDate) > 0) {
                System.out.println("ddd");
                reservation.setApprovalStatus(ReservationType.COMPLETED);
            }
        }
    }

    @Override
    @Modifying
    @Transactional
    public List<HostTableDto> getTableInfo(int hostId, int houseId, int month) {
        List<Reservation> reservation = reservationRepository.findByHostId(hostId);
        changeCompletedType(reservation);
        return qlrmRepository.returnDataList(queryStorage.getlist(hostId, houseId, month), HostTableDto.class);
    }

    @Override
    @Modifying
    @Transactional
    public List<HostTableDto> getTableInfo(int hostId, int month) {
        List<Reservation> reservation = reservationRepository.findByHostId(hostId);
        changeCompletedType(reservation);
        return qlrmRepository.returnDataList(queryStorage.getlist(hostId, month), HostTableDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookedDate> getListBookedDate(int houseid) {
        return bookedDateRepository.findAllByHouseId(houseid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HouseWaitDto> getWaitCount(int hostid) {
        return qlrmRepository.returnDataList(queryStorage.getWaitCount(hostid), HouseWaitDto.class);
    }

    @Override
    @Transactional
    public void cancelReservation(int id) {
        Reservation res = reservationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재 하지 않는 예약 입니다.")
        );
        res.setHostId(null);
        res.setGuestId(null);
        res.setHouseId(null);
        bookedDateRepository.deleteAllByResId(id);
        reservationRepository.deleteById(id);
    }

    @Override
    @Modifying
    @Transactional
    public void changeResType(ApproveDto approveDto) {
        Reservation reservation = findByResId(approveDto.getResId());
        reservation.setApprovalStatus(parseResEnumType(approveDto.getApprove()));
    }

    private ReservationType parseResEnumType(String type) {
        ReservationType enumType = ReservationType.WAITING;
        switch (type) {
            case "WAITING":
                break;
            case "PAID":
                enumType = ReservationType.PAID;
                break;
            case "APPROVED":
                enumType = ReservationType.APPROVED;
                break;
            case "COMPLETED":
                enumType = ReservationType.COMPLETED;
        }
        return enumType;
    }

    public Reservation findByResId(int resId) {
        return reservationRepository.findById(resId).orElseThrow(() -> new RuntimeException("해당 예약을 찾을 수 없습니다."));

    }

    @Override
    @Modifying
    @Transactional
    public void kakaoPaymentApprove(int resId) {
        Reservation res = reservationRepository.findById(resId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 예약입니다.")
        );
        if (res == null) {
            return;
        }
        res.setApprovalStatus(ReservationType.PAID);
    }
}