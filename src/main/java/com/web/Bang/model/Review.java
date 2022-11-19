package com.web.Bang.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    /**
     * 게스트가 올리는 숙소 리뷰
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "houseId")
    @JsonIgnoreProperties({"reviews", "hostId"})
    private House houseId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "guestId")
    private User guestId;

    @Lob
    private String content;

    @ColumnDefault("0.0")
    private double starScore;

    @CreationTimestamp
    private Timestamp creationDate;

    @OneToMany(mappedBy = "reviewId", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"reviewId"})
    @OrderBy("id DESC")
    private List<Reply> replies;

}
