package com.web.Bang.controller.apicontroller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.ResponseDto;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Review;
import com.web.Bang.service.HouseServiceImpl;
import com.web.Bang.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewApiController {

    private final ReviewServiceImpl reviewService;

    private final HouseServiceImpl houseService;

    // 리뷰 작성 기능
    @PostMapping("/post/{houseId}")
    public ResponseDto<Review> postReview(@RequestBody Review review, @PathVariable int houseId,
                                          @AuthenticationPrincipal PrincipalDetail principalDetail) {
        review.setHouseId(houseService.findById(houseId));
        Review reviewEntity = reviewService.postReview(review, principalDetail.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), reviewEntity);
    }

    // 리뷰 수정 기능
    @PutMapping("/update/{reviewId}")
    public ResponseDto<Review> updateReview(@PathVariable int reviewId,
                                            @RequestBody Review review) {
        Review reviewEntity = reviewService.updateReview(reviewId, review);
        return new ResponseDto<>(HttpStatus.OK.value(), reviewEntity);
    }


    // 리뷰 삭제 기능
    @DeleteMapping("/delete/{reviewId}")
    public ResponseDto<Integer> deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    // 댓글 작성 기능
    @PostMapping("/reply/{reviewId}")
    public ResponseDto<Reply> addReply(@PathVariable int reviewId, @RequestBody Reply reply,
                                       @AuthenticationPrincipal PrincipalDetail principalDetail) {
        if (principalDetail.getUser().getReportCount() > 2) {
            return new ResponseDto<>(HttpStatus.FORBIDDEN.value(), null);
        }
        Reply replyEntity = reviewService.addReply(reviewId, reply);
        return new ResponseDto<>(HttpStatus.OK.value(), replyEntity);
    }

    // 댓글 수정 기능
    @PutMapping("/reply/{replyId}")
    public ResponseDto<Reply> updateReply(@PathVariable int replyId, @RequestBody Reply reply) {
        Reply replyEntity = reviewService.updateReply(replyId, reply);
        return new ResponseDto<>(HttpStatus.OK.value(), replyEntity);
    }

    // 댓글 삭제 기능
    @DeleteMapping("/reply/{replyId}")
    public ResponseDto<Integer> deleteReply(@PathVariable int replyId) {
        reviewService.deleteReply(replyId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    // 리뷰 리스트
    @GetMapping("/list/{houseId}")
    public List<Review> getReviewList(@PathVariable int houseId) {
        return reviewService.getReviewListByHouseId(houseId);
    }

}
