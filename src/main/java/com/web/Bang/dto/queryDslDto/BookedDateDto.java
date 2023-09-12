package com.web.Bang.dto.queryDslDto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookedDateDto {

    private int id;

    private com.web.Bang.model.Reservation Reservation;

    private LocalDate bookedDate;

    @QueryProjection
    public BookedDateDto(int id, com.web.Bang.model.Reservation reservation, LocalDate bookedDate) {
        this.id = id;
        this.Reservation = reservation;
        this.bookedDate = bookedDate;
    }

}
