package com.web.Bang.controller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.RequestPostDto;
import com.web.Bang.dto.queryDslDto.HouseDto;
import com.web.Bang.dto.queryDslDto.ReviewDto;
import com.web.Bang.model.House;
import com.web.Bang.model.WishList;
import com.web.Bang.service.HouseServiceImpl;
import com.web.Bang.service.ReviewServiceImpl;
import com.web.Bang.service.WishListServiceImpl;
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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HouseController {

    private final HouseServiceImpl houseService;
    private final ReviewServiceImpl reviewService;
    private final WishListServiceImpl wishListService;

    // 숙소 리스트 페이지 호출
    @GetMapping("/user/house-list")
    public String getHouseList(Model model, String address, String type) {

        // 지역별, 유형별 숙소 검색
        List<HouseDto> houseList;

        address = (address == null) ? "" : address;
        type = type == null ? "" : type;

        if (address.isEmpty() && type.isEmpty()) {
            houseList = houseService.getHouseList();
        } else if (address.isEmpty() || type.isEmpty()) {
            houseList = houseService.searchHouseByAddressOrType(address, type);
        } else {
            houseList = houseService.searchHouseByAddressAndType(address, type);
        }

        model.addAttribute("houseList", houseList);
        model.addAttribute("searchedAddress", address);
        model.addAttribute("searchedType", type);

        return "house/list_form";
    }

    // 숙소 상세정보 페이지 호출
    @GetMapping("/user/house-detail/{houseId}")
    public String getHouseDetail(@PathVariable("houseId") int houseId, Model model,
                                 @AuthenticationPrincipal PrincipalDetail principalDetail,
                                 @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
        // 조회한 숙소
        House houseEntity = houseService.getHouseDetail(houseId);
        // 조회한 숙소와 같은 지역의 숙소 목록
        List<HouseDto> houseList = houseService.getHouseListByAddress(houseEntity.getAddress(), houseEntity.getId());
        // 조회한 숙소의 리뷰 목록
        Page<ReviewDto> reviews = reviewService.getReviewPageByHouseId(houseId, pageable);
        // 숙소 리뷰의 총 개수
        int reviewCount = houseService.getReviewCount(houseId);
        // 조회한 사용자가 해당 숙소를 위시리스트에 넣었는지
        WishList wishListEntity = null;
        if (principalDetail != null) {
            wishListEntity = wishListService.checkWishList(houseId, principalDetail.getUser().getId());
        }
        // 숙소의 평균 평점
        ReviewDto reviewDto = reviewService.getAvgStarScore(houseId) == null ? new ReviewDto() : reviewService.getAvgStarScore(houseId);
        // 위시리스트 카운트
        int likeCount = wishListService.getLikeCount(houseId);

        model.addAttribute("house", houseEntity);
        model.addAttribute("houseList", houseList);
        model.addAttribute("reviews", reviews);
        model.addAttribute("likeHouse", wishListEntity);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("avgScore", reviewDto.getStarScore());
        model.addAttribute("likeCount", likeCount);
        return "house/detail_form";
    }

    // 숙소 등록 페이지 호출
    @GetMapping("/host/post_form")
    public String getPostingForm() {
        return "house/post_form";
    }

    // 숙소 글 수정 페이지 호출
    @GetMapping("/host/update_form/{houseId}")
    public String getUpdateForm(@PathVariable int houseId, Model model) {
        House houseEntity = houseService.getHouseDetail(houseId);
        model.addAttribute("house", houseEntity);
        return "house/update_house_form";
    }

    @PostMapping("/host/post-house")
    public String postHouse(RequestPostDto requestPostDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        houseService.postHouse(requestPostDto, principalDetail.getUser());
        return "redirect:/host/house-management";
    }

    @PostMapping("/host/update-house/{houseId}")
    public String updateHouse(@PathVariable int houseId, RequestPostDto requestPostDto) {
        houseService.updateHouse(houseId, requestPostDto);
        return "redirect:/host/house-management";
    }

    // 숙소 관리 폼 호출 (호스트)
    @GetMapping("/host/house-management")
    public String getHouseManagementForm(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        model.addAttribute("houseList", houseService.findAllByHostId(principalDetail.getUser().getId()));
        return "house/house_management_form";
    }

    @PostMapping("/host/house-update/{houseId}")
    public String updateHouse(@PathVariable int houseId, RequestPostDto requestPostDto,
                              @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        // 숙소 정보 수정 기능
        System.out.println(requestPostDto);
        houseService.updateHouse(houseId, requestPostDto);
        model.addAttribute("houseList", houseService.findAllByHostId(principalDetail.getUser().getId()));
        return "house/house_management_form";
    }


}
