package com.web.Bang.controller.apicontroller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.ResponseDto;
import com.web.Bang.dto.kakao.Document;
import com.web.Bang.dto.kakao.KakaoSearch;
import com.web.Bang.model.House;
import com.web.Bang.model.Review;
import com.web.Bang.service.HouseService;
import com.web.Bang.service.ReviewService;
import com.web.Bang.service.WishListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/house")
public class HouseApiController {

    private final HouseService houseService;

    private final ReviewService reviewService;

    private final WishListService wishListService;

    public HouseApiController(HouseService houseService, ReviewService reviewService, WishListService wishListService) {
        this.houseService = houseService;
        this.reviewService = reviewService;
        this.wishListService = wishListService;
    }


    @DeleteMapping("/delete/{houseId}")
    public ResponseDto<Integer> deleteHouse(@PathVariable int houseId) {
        // 숙소 삭제 기능
        System.out.println("delete: " + houseId);
        houseService.deleteHouse(houseId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/wish-list")
    public ResponseDto<Integer> addWishList(@RequestBody House house,
                                            @AuthenticationPrincipal PrincipalDetail principalDetail) {
        // 위시리스트 추가 기능
        System.out.println("위시리스트 추가 확인");
        houseService.addWishList(house.getId(), principalDetail.getUser());
        int likeCount = wishListService.getLikeCount(house.getId());

        return new ResponseDto<>(HttpStatus.OK.value(), likeCount);
    }

    @DeleteMapping("/wish-list/{houseId}")
    public ResponseDto<Integer> deleteItemOfWishList(@PathVariable int houseId,
                                                     @AuthenticationPrincipal PrincipalDetail principalDetail) {
        System.out.println("위시리스트 삭제 확인");
        houseService.deleteItemOfWishList(houseId, principalDetail.getUser().getId());
        int likeCount = wishListService.getLikeCount(houseId);

        return new ResponseDto<>(HttpStatus.OK.value(), likeCount);
    }

    @GetMapping("/review-list/{houseId}")
    public Page<Review> getReviewPage(@PathVariable int houseId, Model model,
                                      @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {

        // 조회한 숙소의 리뷰 목록
        return reviewService.getReviewPageByHouseId(houseId, pageable);
    }

    @GetMapping("/house-xy")
    public ResponseDto<Document> findHouseLocation(@RequestParam("houseId") int id) {
        House house = houseService.findById(id);
        System.out.println(house.getName());
        KakaoSearch kakao = requestXYLocation(house);
        return new ResponseDto<>(HttpStatus.OK.value(), kakao.getDocuments().get(0));
    }

    private KakaoSearch requestXYLocation(House house) {
        RestTemplate transmitter = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "KakaoAK c5cc80a8d2ddf2649e90f4681bdebb6c");
        HttpEntity<MultiValueMap<String, String>> message = new HttpEntity<>(header);
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + house.getDetailAddress();

        ResponseEntity<KakaoSearch> response = transmitter.exchange(url,
                HttpMethod.GET, message, KakaoSearch.class);
        System.out.println(response);
        return response.getBody();
    }
}
