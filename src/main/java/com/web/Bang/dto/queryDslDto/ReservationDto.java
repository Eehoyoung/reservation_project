package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.Bang.model.House;
import com.web.Bang.model.User;
import com.web.Bang.model.type.ReservationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {

    private int id;

    private User guestId;

    private User hostId;

    private House houseId;

    private Date checkInDate;

    private Date checkOutDate;

    private int headCount;

    private int price;

    private Timestamp creationDate;

    private String request;

    private ReservationType approvalStatus; // 예약 승인 상태

    private int[] tempIdBox = new int[3]; // userid, hostid, houseid

    @QueryProjection
    public ReservationDto(int id, User guestId, User hostId, House houseId, Date checkInDate, Date checkOutDate, int headCount
            , int price, Timestamp creationDate, String request, ReservationType approvalStatus) {
        this.id = id;
        this.guestId = guestId;
        this.hostId = hostId;
        this.houseId = houseId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.headCount = headCount;
        this.price = price;
        this.creationDate = creationDate;
        this.request = request;
        this.approvalStatus = approvalStatus;
    }
}
