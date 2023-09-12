package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class HostTableDto {
    private int id;
    private String username;
    private int headCount;
    private Date checkInDate;
    private Date checkOutDate;
    private int price;
    private String phoneNumber;
    private String request;
    private String approvalStatus;
    private int houseId;
    private String houseName;

    @QueryProjection
    public HostTableDto(int id, String username, int headCount, Date checkInDate, Date checkOutDate, int price,
                        String phoneNumber, String request, String approvalStatus, int houseId, String houseName) {
        this.id = id;
        this.username = username;
        this.headCount = headCount;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.request = request;
        this.approvalStatus = approvalStatus;
        this.houseId = houseId;
        this.houseName = houseName;
    }

    @QueryProjection
    public HostTableDto(int id, String username, int headCount, Date checkInDate, Date checkOutDate,
                        String phoneNumber, String request, String approvalStatus, int houseId, String houseName) {
        this.id = id;
        this.username = username;
        this.headCount = headCount;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.phoneNumber = phoneNumber;
        this.request = request;
        this.approvalStatus = approvalStatus;
        this.houseId = houseId;
        this.houseName = houseName;
    }

    @QueryProjection
    public HostTableDto(int id, int houseId) {
        this.id = id;
        this.houseId = houseId;
    }
}
