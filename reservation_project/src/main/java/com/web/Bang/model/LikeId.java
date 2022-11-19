package com.web.Bang.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class LikeId implements Serializable {

    private int houseId;
    private int guestId;
}
