package com.web.Bang.repository;

import com.web.Bang.dto.HouseWaitDto;
import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.HostTableDto;
import com.web.Bang.dto.queryDslDto.ReservationDto;

import java.util.List;

public interface ReservationRepositoryCustom {


    List<ReservationDto> findByGuestId(int guestId);

    List<ReservationDto> findByHostId(int id);

    List<AdmintableDto> loadReservationMonthTableCount();

    List<HostTableDto> getlist(int hostId, int houseId, int month);

    List<HostTableDto> getlist(int hostId, int month);

    List<HouseWaitDto> getWaitCount(int id);


}
