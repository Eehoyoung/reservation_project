package com.web.Bang.repository;

import com.web.Bang.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

    @Query("SELECT count(*) FROM Review r WHERE r.houseId.id= :houseId")
    Optional<Integer> getReviewCount(@Param("houseId") int houseId);

    @Query("SELECT COUNT(*) FROM Review r WHERE r.guestId.id = :guestId")
    Optional<Integer> getReviewCountByGuestId(@Param("guestId") int guestId);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.id = :reviewId")
    void deleteReview(@Param("reviewId") int reviewId);

}
