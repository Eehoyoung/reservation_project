package com.web.Bang.controller.apicontroller;

import com.web.Bang.dto.*;
import com.web.Bang.model.Reservation;
import com.web.Bang.service.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationApiController {

    final
    HttpSession httpSession;

    private final ReservationServiceImpl reservationService;

    // 예약을 하는 기능
    @PostMapping("/book")
    public ResponseDto<String> reserveHouse(@RequestBody Reservation reservation) {
        reservationService.makeReservation(reservation);
        return new ResponseDto<>(HttpStatus.OK.value(), "OK");
    }

    // 호스트 예약관리 테이블에 정보를 가져오는 기능
    @GetMapping("/detail")
    public List<HostTableDto> getHouseReservation(@RequestParam(value = "hostId") int hostId, @RequestParam(value = "houseId") int houseId,
                                                  @RequestParam(value = "month") int month) {
        List<HostTableDto> result;
        if (String.valueOf(houseId).isEmpty()) {
            result = reservationService.getTableInfo(hostId, month);
        } else {
            result = reservationService.getTableInfo(hostId, houseId, month);
        }

        return result;
    }


    // 호스트 측 예약 취소 기능
    @DeleteMapping("/delete/{reservationId}")
    public ResponseDto<Integer> deleteReservation(@PathVariable int reservationId) {
        reservationService.cancelReservation(reservationId);
        return new ResponseDto<>(HttpStatus.OK.value(), reservationId);
    }

    // 호스트 예약 승인 기능
    @PostMapping("/approve")
    public ResponseDto<Integer> approveRes(@RequestBody ApproveDto approveDto) {
        reservationService.changeResType(approveDto);
        return new ResponseDto<>(HttpStatus.ACCEPTED.value(), approveDto.getResId());
    }

    // 예약에 대한 디테일 정보를 가져오는 기능
    @GetMapping("/detail/{resId}")
    public Reservation showResDetail(@PathVariable int resId) {
        return reservationService.findByResId(resId);
    }

    // 결제를 요청하는 기능
    @PostMapping("/kakao-pay")
    public KaKaoApproveDto payForKaKao(@RequestBody ResponsePaidDto paidDto) {
        Reservation res = reservationService.findByResId(paidDto.getResId());
        int price = reservationService.getRangeDay(res.getCheckInDate(), res.getCheckOutDate())
                * res.getHouseId().getOneDayPrice();
        paidDto.setPrice(price);
        paidDto.setGuestName(res.getGuestId().getUsername());
        paidDto.setHostName(res.getHostId().getUsername());
        paidDto.setHouseName(res.getHouseId().getName());
        paidDto.setHouseId(res.getHouseId().getId());
        KaKaoApproveDto approveDto = requestReadyForKaKaoPay(paidDto.getGuestName(), paidDto.getHostName(),
                paidDto.getHouseName(), paidDto.getPrice());
        paidDto.setTid(approveDto.getTid());
        httpSession.setAttribute("kakao", paidDto);
        return approveDto;
    }

    // 카카오 API를 사용하여서 카카오 측에게 Post Message를 던지는
    private KaKaoApproveDto requestReadyForKaKaoPay(String guestName, String hostName, String houseName, int price) {
        RestTemplate transmitter = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "KakaoAK c5cc80a8d2ddf2649e90f4681bdebb6c");
        header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("cid", "TC0ONETIME");
        param.add("partner_order_id", hostName);
        param.add("partner_user_id", guestName);
        param.add("item_name", houseName);
        param.add("quantity", "1");
        param.add("total_amount", String.valueOf(price));
        param.add("tax_free_amount", "0");
        param.add("approval_url", "http://localhost:9090/guest/kakao/approve");
        param.add("cancel_url", "http://localhost:9090/user/error");
        param.add("fail_url", "http://localhost:9090/user/error");

        HttpEntity<MultiValueMap<String, String>> message = new HttpEntity<>(param, header);
        ResponseEntity<KaKaoApproveDto> response = transmitter.exchange("https://kapi.kakao.com/v1/payment/ready",
                HttpMethod.POST, message, KaKaoApproveDto.class);
        return response.getBody();
    }


}
