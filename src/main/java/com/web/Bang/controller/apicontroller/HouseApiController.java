package com.web.Bang.controller.apicontroller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.KakaoSearchDto;
import com.web.Bang.dto.ResponseDto;
import com.web.Bang.dto.kakao.Document;
import com.web.Bang.dto.queryDslDto.ReviewDto;
import com.web.Bang.model.House;
import com.web.Bang.service.HouseServiceImpl;
import com.web.Bang.service.ReviewServiceImpl;
import com.web.Bang.service.WishListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/house")
public class HouseApiController {

    private final HouseServiceImpl houseService;

    private final ReviewServiceImpl reviewService;

    private final WishListServiceImpl wishListService;

    @DeleteMapping("/delete/{houseId}")
    public ResponseDto<Integer> deleteHouse(@PathVariable int houseId) {
        // 숙소 삭제 기능
        houseService.deleteHouse(houseId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/wish-list")
    public ResponseDto<Integer> addWishList(@RequestBody Map<String, Integer> map,
                                            @AuthenticationPrincipal PrincipalDetail principalDetail) {
        // 위시리스트 추가 기능
        houseService.addWishList(map.get("id"), principalDetail.getUser());
        int likeCount = wishListService.getLikeCount(map.get("id"));

        return new ResponseDto<>(HttpStatus.OK.value(), likeCount);
    }

    @DeleteMapping("/wish-list/{houseId}")
    public ResponseDto<Integer> deleteItemOfWishList(@PathVariable int houseId,
                                                     @AuthenticationPrincipal PrincipalDetail principalDetail) {
        houseService.deleteItemOfWishList(houseId, principalDetail.getUser().getId());
        int likeCount = wishListService.getLikeCount(houseId);

        return new ResponseDto<>(HttpStatus.OK.value(), likeCount);
    }

    @GetMapping("/review-list/{houseId}")
    public Page<ReviewDto> getReviewPage(@PathVariable int houseId,
                                         @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {

        // 조회한 숙소의 리뷰 목록
        return reviewService.getReviewPageByHouseId(houseId, pageable);
    }

    @GetMapping("/house-xy")
    public ResponseDto<Document> findHouseLocation(@RequestParam("houseId") int id) {
        House house = houseService.findById(id);
        KakaoSearchDto kakao = requestXYLocation(house);
        return new ResponseDto<>(HttpStatus.OK.value(), kakao.getDocuments().get(0));
    }

    private KakaoSearchDto requestXYLocation(House house) {
        RestTemplate transmitter = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "KakaoAK c5cc80a8d2ddf2649e90f4681bdebb6c");
        HttpEntity<MultiValueMap<String, String>> message = new HttpEntity<>(header);
        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + house.getDetailAddress();

        ResponseEntity<KakaoSearchDto> response = transmitter.exchange(url,
                HttpMethod.GET, message, KakaoSearchDto.class);
        return response.getBody();
    }
}
