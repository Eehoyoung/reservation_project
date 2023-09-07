package com.web.Bang.controller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.DateModelDto;
import com.web.Bang.dto.HouseWaitDto;
import com.web.Bang.dto.ResponsePaidDto;
import com.web.Bang.dto.kakao.KaKaoPayResponseDto;
import com.web.Bang.model.BookedDate;
import com.web.Bang.model.House;
import com.web.Bang.model.Reservation;
import com.web.Bang.model.User;
import com.web.Bang.service.HouseServiceImpl;
import com.web.Bang.service.ReservationServiceImpl;
import com.web.Bang.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final HttpSession httpSession;
    private final UserServiceImpl userService;
    private final HouseServiceImpl houseService;
    private final ReservationServiceImpl reservationService;

    @GetMapping("/guest/bookForm/{houseid}")
    public String reserveHouse(@PathVariable int houseid, Model model) {
        House house = houseService.getHouseDetail(houseid);

        int index = 0;
        ArrayList<DateModelDto> dates = new ArrayList<>();
        for (BookedDate date : reservationService.getListBookedDate(houseid)) {
            DateModelDto dto = new DateModelDto();
            dto.setDate(date.getBookedDate());
            dto.setIndex(index);
            dates.add(dto);
            index++;
        }

        model.addAttribute("house", house);
        model.addAttribute("bookedDates", dates);
        model.addAttribute("size", dates.size());

        return "input";
    }

    // 호스트 예약 테이블을 그려주는데 필요한 데이터를 보내주는 기능
    @GetMapping("/host/reserveTable")
    public String reserveHostTable(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        List<HouseWaitDto> count = reservationService.getWaitCount(principalDetail.getUser().getId());
        List<House> houses = houseService.findAllByHostId(principalDetail.getUser().getId());
        model.addAttribute("houses", houses);
        model.addAttribute("count", count);
        return "reservation/hostReserveTable";
    }

    // 유저 예약 테이블을 그려주는데 필요한 데이터를 보내주는 기능
    @GetMapping("/guest/reserveTable")
    public String reserveUserTable(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        User user = userService.findByUserId(principalDetail.getUser().getId());
        List<Reservation> res = reservationService.getReservation(user);
        model.addAttribute("reservations", res);
        return "reservation/userReservationTable";
    }

    // 카카오 결제 완료 시 redirect 되는 주소
    @GetMapping("/guest/kakao/approve")
    public String approve(@RequestParam String pg_token, Model model) {
        ResponsePaidDto paidDto = (ResponsePaidDto) httpSession.getAttribute("kakao");
        httpSession.removeAttribute("kakao");
        ResponseEntity<KaKaoPayResponseDto> response = requestKakaoPaymentApprove(pg_token, paidDto);
        KaKaoPayResponseDto dto = response.getBody();

        if (response.getStatusCode() == HttpStatus.OK) {
            reservationService.kakaoPaymentApprove(paidDto.getResId());
            Reservation res = reservationService.findByResId(paidDto.getResId());
            Objects.requireNonNull(dto).setApproved_at(dto.getApproved_at().replace("T", " "));
            model.addAttribute("kakao", dto);
            model.addAttribute("reservation", res);

            return "/reservation/paymentCompletePage";
        } else {
            return "erroPage";
        }
    }

    // 카카오 결제 완료 승인을 카카오 페이에게 요청하는 기능
    private ResponseEntity<KaKaoPayResponseDto> requestKakaoPaymentApprove(String pg_token, ResponsePaidDto paidDto) {
        RestTemplate transmitter = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "KakaoAK c5cc80a8d2ddf2649e90f4681bdebb6c");
        header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("cid", "TC0ONETIME");
        param.add("tid", paidDto.getTid());
        param.add("partner_order_id", paidDto.getHostName());
        param.add("partner_user_id", paidDto.getGuestName());
        param.add("pg_token", pg_token);

        HttpEntity<MultiValueMap<String, String>> message = new HttpEntity<>(param, header);

        return transmitter.exchange("https://kapi.kakao.com/v1/payment/approve", HttpMethod.POST, message, KaKaoPayResponseDto.class);
    }

    // 예약완료시 예약에 관련된 어드바이스를 보여주는 기능
    @GetMapping("/guest/advice")
    public String showAdvice() {
        return "/advice/reservationAdvice";
    }

}
