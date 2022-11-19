package com.web.Bang.service;

import com.web.Bang.dto.HouseScoreDto;
import com.web.Bang.model.House;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import com.web.Bang.repository.HouseRepository;
import com.web.Bang.repository.ReplyRepository;
import com.web.Bang.repository.ReviewRepository;
import com.web.Bang.repository.queryStorage.QlrmRepository;
import com.web.Bang.repository.queryStorage.StarScoreQueryStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReplyRepository replyRepository;

    private final QlrmRepository<HouseScoreDto> qlrmRepository;

    private final StarScoreQueryStorage queryStorage;

    private final HouseRepository houseRepository;

    public ReviewService(ReviewRepository reviewRepository, ReplyRepository replyRepository, QlrmRepository<HouseScoreDto> qlrmRepository, StarScoreQueryStorage queryStorage, HouseRepository houseRepository) {
        this.reviewRepository = reviewRepository;
        this.replyRepository = replyRepository;
        this.qlrmRepository = qlrmRepository;
        this.queryStorage = queryStorage;
        this.houseRepository = houseRepository;
    }

    @Transactional
    public Review postReview(Review review, User user) {

        // 저장된 별점 데이터 꺼내서 평점 계산
        Review reviewEntity = reviewRepository.save(review);

        House houseEntity = houseRepository.findById(review.getHouseId().getId()).get();
        double avgStarScore = qlrmRepository
                .returnDataList(queryStorage.getAvgStarScoreByHouse(houseEntity.getId()), HouseScoreDto.class)
                .get(0).getScore();

        houseEntity.setStarScore(avgStarScore);
        review.setGuestId(user);

        return reviewEntity;
    }

    @Transactional
    public Review getReviewDetail(int reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));
    }

    @Transactional
    public Reply addReply(int reviewId, Reply requestReply, User user) {
        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));

        requestReply.setReviewId(reviewEntity);
        reviewEntity.getReplies().add(requestReply);

        return replyRepository.save(requestReply);
    }

    @Transactional
    public Page<Review> getReviewPageByHouseId(int houseId, Pageable pageable) {
        return reviewRepository.findAllByHouseId(houseId, pageable);
    }

    @Transactional
    public HouseScoreDto getAvgStarScore(int houseId) {
        try {
            return qlrmRepository.returnDataList(queryStorage.getAvgStarScoreByHouse(houseId), HouseScoreDto.class).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    @Transactional
    public Reply updateReply(int replyId, Reply reply) {
        Reply replyEntity = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("삭제된 댓글입니다."));

        replyEntity.setContent(reply.getContent());
        replyRepository.save(replyEntity);
        return replyEntity;
    }

    @Transactional
    public void deleteReply(int replyId) {
        replyRepository.deleteByReplyId(replyId);
    }

    @Transactional
    public void deleteReview(int reviewId) {
        reviewRepository.deleteReview(reviewId);
    }

    @Transactional
    public Review updateReview(int reviewId, Review review) {
        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));

        reviewEntity.setContent(review.getContent());
        reviewEntity.setStarScore(review.getStarScore());

        return reviewEntity;
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewListByHouseId(int houseId) {
        return reviewRepository.findAllByHouseId(houseId);
    }

    @Transactional(readOnly = true)
    public Page<Review> getReviewListByGuestId(int guestId, Pageable pageable) {
        return reviewRepository.findAllByGuestId(guestId, pageable);
    }

}
