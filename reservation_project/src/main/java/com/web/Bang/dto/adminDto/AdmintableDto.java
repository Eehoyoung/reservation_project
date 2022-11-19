package com.web.Bang.dto.adminDto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AdmintableDto {
    private String column;
    private int count;


    public AdmintableDto(String name, String username, BigInteger count) {
        this.column = name + "(" + username + ")";
        this.count = count.intValue();
    }

    public AdmintableDto(int month, BigInteger count) {
        this.column = String.valueOf(month);
        this.count = count.intValue();
    }

    public AdmintableDto(String address, BigInteger count) {
        this.column = address;
        this.count = count.intValue();
    }


}
