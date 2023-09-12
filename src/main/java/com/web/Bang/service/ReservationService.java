package com.web.Bang.service;

import com.web.Bang.dto.ApproveDto;
import com.web.Bang.dto.HouseWaitDto;
import com.web.Bang.dto.queryDslDto.BookedDateDto;
import com.web.Bang.dto.queryDslDto.HostTableDto;
import com.web.Bang.dto.queryDslDto.ReservationDto;
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
    List<ReservationDto> getReservation(User user);

    @Modifying
    @Transactional
    List<HostTableDto> getTableInfo(int hostId, int houseId, int month);

    @Modifying
    @Transactional
    List<HostTableDto> getTableInfo(int hostId, int month);

    @Transactional(readOnly = true)
    List<BookedDateDto> getListBookedDate(int houseid);

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
