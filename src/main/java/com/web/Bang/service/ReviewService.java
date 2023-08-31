package com.web.Bang.service;

import com.web.Bang.dto.HouseScoreDto;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewService {
    @Transactional
    Review postReview(Review review, User user);

    @Transactional
    Review getReviewDetail(int reviewId);

    @Transactional
    Reply addReply(int reviewId, Reply requestReply);

    @Transactional
    Page<Review> getReviewPageByHouseId(int houseId, Pageable pageable);

    @Transactional
    HouseScoreDto getAvgStarScore(int houseId);

    @Transactional
    Reply updateReply(int replyId, Reply reply);

    @Transactional
    void deleteReply(int replyId);

    @Transactional
    void deleteReview(int reviewId);

    @Transactional
    Review updateReview(int reviewId, Review review);

    @Transactional(readOnly = true)
    List<Review> getReviewListByHouseId(int houseId);

    @Transactional(readOnly = true)
    Page<Review> getReviewListByGuestId(int guestId, Pageable pageable);
}
