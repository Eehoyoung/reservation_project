package com.web.Bang.service;

import com.web.Bang.dto.queryDslDto.ReviewDto;
import com.web.Bang.model.House;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import com.web.Bang.repository.HouseRepository;
import com.web.Bang.repository.ReplyRepository;
import com.web.Bang.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReplyRepository replyRepository;

    private final HouseRepository houseRepository;

    @Override
    @Transactional
    public Review postReview(Review review, User user) {

        // 저장된 별점 데이터 꺼내서 평점 계산
        Review reviewEntity = reviewRepository.save(review);

        House houseEntity = houseRepository.findById(review.getHouseId().getId()).orElseThrow(
                () -> new IllegalArgumentException("존재 하지 않는 숙소 입니다.")
        );
        double avgStarScore = reviewRepository.getAvgStarScoreByHouse(review.getHouseId().getId()).get(0).getStarScore();

        houseEntity.setStarScore(avgStarScore);
        review.setGuestId(user);

        return reviewEntity;
    }

    @Override
    @Transactional
    public Review getReviewDetail(int reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));
    }

    @Override
    @Transactional
    public Reply addReply(int reviewId, Reply requestReply) {
        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));

        requestReply.setReviewId(reviewEntity);
        reviewEntity.getReplies().add(requestReply);

        return replyRepository.save(requestReply);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDto> getReviewPageByHouseId(int houseId, Pageable pageable) {
        return reviewRepository.findAllByHouseId(houseId, pageable).map(review -> new ReviewDto(
                review.getId(),
                review.getHouseId(),
                review.getGuestId(),
                review.getContent(),
                review.getStarScore(),
                review.getCreationDate(),
                new ArrayList<>(review.getReplies())
        ));
    }
    @Override
    @Transactional
    public ReviewDto getAvgStarScore(int houseId) {
        try {
            return reviewRepository.getAvgStarScoreByHouse(houseId).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    @Override
    @Transactional
    public Reply updateReply(int replyId, Reply reply) {
        Reply replyEntity = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("삭제된 댓글입니다."));

        replyEntity.setContent(reply.getContent());
        replyRepository.save(replyEntity);
        return replyEntity;
    }

    @Override
    @Transactional
    public void deleteReply(int replyId) {
        replyRepository.deleteByReplyId(replyId);
    }

    @Override
    @Transactional
    public void deleteReview(int reviewId) {
        reviewRepository.deleteReview(reviewId);
    }

    @Override
    @Transactional
    public Review updateReview(int reviewId, Review review) {
        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));

        reviewEntity.setContent(review.getContent());
        reviewEntity.setStarScore(review.getStarScore());

        return reviewEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewListByHouseId(int houseId) {
        return reviewRepository.findAllByHouseId(houseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDto> getReviewListByGuestId(int guestId, Pageable pageable) {
        return reviewRepository.findAllByGuestId(guestId, pageable).map(review -> new ReviewDto(
                review.getId(),
                review.getHouseId(),
                review.getGuestId(),
                review.getContent(),
                review.getStarScore(),
                review.getCreationDate(),
                new ArrayList<>(review.getReplies())));
    }

}
