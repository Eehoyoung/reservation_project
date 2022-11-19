package com.web.Bang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HouseWaitDto {
    private BigInteger wait;
    private Integer houseId;
}
