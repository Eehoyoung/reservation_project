package com.web.Bang.controller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.model.Report;
import com.web.Bang.service.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 나의 신고 내역 페이지
    @GetMapping("/user/report-history")
    public String getMyReportHistoryForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        List<Report> reports = reportService.getReportList(principalDetail.getUser().getId());
        model.addAttribute("reports", reports);
        return "user/report_history_form";
    }

    // 관리자 신고 내역 관리 페이지
    @GetMapping("/admin/report-management")
    public String getReportManagementForm(Model model) {
        model.addAttribute("reports", reportService.getAllReport());
        return "admin/report_management_form";
    }
}
