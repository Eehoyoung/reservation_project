package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.Bang.model.House;
import com.web.Bang.model.Reply;
import com.web.Bang.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private int id;

    private House houseId;

    private User guestId;

    private String content;

    private double starScore;

    private Timestamp creationDate;

    private List<Reply> replies;

    @QueryProjection
    public ReviewDto(int id, House houseId, User guestId, String content, double starScore, Timestamp creationDate, List<Reply> replies) {
        this.id = id;
        this.houseId = houseId;
        this.guestId = guestId;
        this.content = content;
        this.starScore = starScore;
        this.creationDate = creationDate;
        this.replies = replies;
    }

    @QueryProjection
    public ReviewDto(House houseId, double starScore) {
        this.houseId = houseId;
        this.starScore = starScore;
    }
}
