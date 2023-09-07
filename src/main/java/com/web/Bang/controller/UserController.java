package com.web.Bang.controller;

import com.web.Bang.auth.PrincipalDetailService;
import com.web.Bang.service.HouseServiceImpl;
import com.web.Bang.service.UserServiceImpl;
import com.web.Bang.service.WishListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PrincipalDetailService detailService;
    private final UserServiceImpl userService;
    private final WishListServiceImpl wishListService;
    private final HouseServiceImpl houseService;
    private final AuthenticationManager authenticationManager;

    @Value("${kakao.key}")
    private String kakaoPassword;


    @GetMapping({"", "/"})
    public String home(Model model) {
        // Best 3 가져오기
        model.addAttribute("houses", houseService.getHouseOrderByStarScore());
        return "home";
    }

    @GetMapping("/user/update_form")
    public String updateForm() {
        return "user/update_user_form";
    }

    // 위시리스트 페이지 호출
    @GetMapping("/guest/wish-list/{guestId}")
    public String getWishList(@PathVariable int guestId, Model model) {
        model.addAttribute("wishList", wishListService.getWishListById(guestId));
        return "user/wish_list_form";
    }

    @GetMapping("/user/behost")
    public String behost() {
        return "/advice/beHost";
    }

    @GetMapping("/user/beguest")
    public String beguest() {
        return "/advice/beGuest";
    }

    @GetMapping("/user/error")
    public String error() {
        return "/advice/errorPage";
    }

    @GetMapping("/user/my-page")
    public String getMyPage() {
        return "user/my_page_form";
    }

    @GetMapping("/test/map-api")
    public String loadMapForm() {
        return "/house/houseMap";
    }

}
