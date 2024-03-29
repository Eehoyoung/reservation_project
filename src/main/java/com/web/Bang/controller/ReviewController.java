package com.web.Bang.controller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.queryDslDto.ReviewDto;
import com.web.Bang.model.Review;
import com.web.Bang.service.HouseServiceImpl;
import com.web.Bang.service.ReservationServiceImpl;
import com.web.Bang.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final HouseServiceImpl houseService;
    private final ReservationServiceImpl reservationService;

    // 리뷰 작성 폼 호출
    @GetMapping("/guest/review/post_form/{reservationId}")
    public String getReviewForm(@PathVariable int reservationId,
                                @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        if (principalDetail.getUser().getReportCount() > 2) {
            return "redirect:/reserveTable/user";
        }
        model.addAttribute("reservation", reservationService.findByResId(reservationId));
        return "review/review_post_form";
    }

    // 리뷰 수정 폼 호출
    @GetMapping("/guest/review/update_form/{reviewId}")
    public String getReviewUpdateForm(@PathVariable int reviewId, Model model) {
        Review reviewEntity = reviewService.getReviewDetail(reviewId);
        model.addAttribute("reviewEntity", reviewEntity);
        return "review/review_update_form";
    }

    // 리뷰 관리 폼 호출
    @GetMapping("/host/review-management/{houseId}")
    public String getMyHouseReviewList(@PathVariable int houseId, Model model,
                                       @PageableDefault(size = 5, sort = "creationDate", direction = Direction.DESC) Pageable pageable) {
        Page<ReviewDto> reviews = reviewService.getReviewPageByHouseId(houseId, pageable);

        int nowPage = reviews.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 2, 1);
        int endPage = Math.min(nowPage + 2, reviews.getTotalPages());

        int reviewCount = houseService.getReviewCount(houseId);

        // 페이지 번호
        ArrayList<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("houseId", houseId);

        return "review/review_management_form";
    }

    // 내가 작성한 리뷰 목록 폼 호출
    @GetMapping("/guest/my-review-list/{guestId}")
    public String getMyReviewList(@PathVariable int guestId, Model model,
                                  @PageableDefault(size = 5, sort = "creationDate", direction = Direction.DESC) Pageable pageable) {
        Page<ReviewDto> reviews = reviewService.getReviewListByGuestId(guestId, pageable);

        int nowPage = reviews.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 2, 1);
        int endPage = Math.min(nowPage + 2, reviews.getTotalPages());

        ArrayList<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }

        int reviewCount = houseService.getReviewCountByGuestId(guestId);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewCount", reviewCount);

        return "user/my_review_list_form";
    }

}
