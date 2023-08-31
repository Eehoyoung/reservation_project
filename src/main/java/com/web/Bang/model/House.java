package com.web.Bang.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String address;

    private String detailAddress;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hostId")
    private User hostId;

    @ColumnDefault("0.0")
    private double starScore;

    private int oneDayPrice; // 하루 숙박 가격

    @Column(nullable = false)
    private String type; // 숙소 유형

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "imageId")
    private Image image;

    @ColumnDefault("1")
    private int capacity;

    @Lob
    private String infoText;

    @CreationTimestamp
    private Timestamp creationDate;

    @OneToMany(mappedBy = "houseId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"houseId", "guestId", "replies"})
    private List<Review> reviews;
}
