package com.web.Bang.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(LikeId.class)
@Table(name = "likehouse")
public class WishList implements Serializable {

    @Id
    @Column(name = "house")
    private int houseId;

    @Id
    @Column(name = "guest")
    private int guestId;

    @OneToOne
    @JoinColumn(name = "guestId", referencedColumnName = "id")
    private User guest;

    @ManyToOne
    @JoinColumn(name = "houseId", referencedColumnName = "id")
    private House house;
}
