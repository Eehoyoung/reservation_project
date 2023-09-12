package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.Bang.model.Image;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HouseDto {

    private int id;

    private String name;

    private String address;

    private String detailAddress;

    private User hostId;

    private double starScore;

    private int oneDayPrice; // 하루 숙박 가격

    private String type; // 숙소 유형

    private Image image;

    private int capacity;

    private String infoText;

    private Timestamp creationDate;

    private List<Review> reviews;

    @QueryProjection
    public HouseDto(int id, String name, String address, String detailAddress, User hostId, double starScore, int oneDayPrice
            , String type, Image image, int capacity, String infoText, Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.detailAddress = detailAddress;
        this.hostId = hostId;
        this.starScore = starScore;
        this.oneDayPrice = oneDayPrice;
        this.type = type;
        this.image = image;
        this.capacity = capacity;
        this.infoText = infoText;
        this.creationDate = creationDate;
    }

}
