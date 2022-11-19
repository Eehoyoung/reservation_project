package com.web.Bang.controller.apicontroller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.ApproveDto;
import com.web.Bang.dto.ResponseDto;
import com.web.Bang.model.Report;
import com.web.Bang.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportApiController {

    private final ReportService reportService;

    public ReportApiController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 게스트가 호스트의 댓글을 신고한다.
     */
    @PostMapping("/reply/{replyId}")
    public ResponseDto<Integer> reportReply(@PathVariable int replyId,
                                            @AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody Report report) {
        reportService.reportReply(principalDetail.getUser(), replyId, report);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    /**
     * 호스트가 게스트의 리뷰를 신고한다.
     */
    @PostMapping("/review/{reviewId}")
    public ResponseDto<Integer> reportReview(@PathVariable int reviewId,
                                             @AuthenticationPrincipal PrincipalDetail principalDetail, @RequestBody Report report) {

        reportService.reportReview(principalDetail.getUser(), reviewId, report);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    /**
     * 관리자가 접수된 신고를 승인
     */
    @PutMapping("/approve/{reportId}")
    public ResponseDto<Report> approveReport(@PathVariable int reportId, @RequestBody ApproveDto approveDto) {
        Report reportEntity = reportService.setReportStatus(approveDto);
        return new ResponseDto<>(HttpStatus.OK.value(), reportEntity);
    }

    /**
     * 관리자가 접수된 신고를 취소
     */
    @PutMapping("/cancel/{reportId}")
    public ResponseDto<Integer> canceleReport(@PathVariable int reportId, @RequestBody ApproveDto approveDto) {
        reportService.setReportStatus(approveDto);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
