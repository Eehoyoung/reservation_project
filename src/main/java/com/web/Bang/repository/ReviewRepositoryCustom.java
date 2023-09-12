package com.web.Bang.repository;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.ReviewDto;
import com.web.Bang.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {

    //    @Query(value = "SELECT * FROM review WHERE houseId = ?", nativeQuery = true)
    Page<Review> findAllByHouseId(int houseId, Pageable pageable);

    //    @Query(value = "SELECT * FROM review WHERE houseId = ?", nativeQuery = true)
    List<ReviewDto> findAllByHouseId(int houseId);

    //    @Query(value = "SELECT * FROM review WHERE guestId = ?", nativeQuery = true)
    Page<Review> findAllByGuestId(int guestId, Pageable pageable);

    List<AdmintableDto> loadReviewMonthTableCount();

    List<ReviewDto> getAvgStarScoreByHouse(int houseId);

}
