package com.web.Bang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
