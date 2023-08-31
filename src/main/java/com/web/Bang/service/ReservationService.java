package com.web.Bang.service;

import com.web.Bang.dto.ApproveDto;
import com.web.Bang.dto.HostTableDto;
import com.web.Bang.dto.HouseWaitDto;
import com.web.Bang.model.BookedDate;
import com.web.Bang.model.Reservation;
import com.web.Bang.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationService {
    @Transactional
    void makeReservation(Reservation reservation);

    @Modifying
    @Transactional
    List<Reservation> getReservation(User user);

    @Modifying
    @Transactional
    List<HostTableDto> getTableInfo(int hostId, int houseId, int month);

    @Modifying
    @Transactional
    List<HostTableDto> getTableInfo(int hostId, int month);

    @Transactional(readOnly = true)
    List<BookedDate> getListBookedDate(int houseid);

    @Transactional(readOnly = true)
    List<HouseWaitDto> getWaitCount(int hostid);

    @Transactional
    void cancelReservation(int id);

    @Modifying
    @Transactional
    void changeResType(ApproveDto approveDto);

    @Modifying
    @Transactional
    void kakaoPaymentApprove(int resId);
}
