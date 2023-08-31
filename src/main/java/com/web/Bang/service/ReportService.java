package com.web.Bang.service;

import com.web.Bang.dto.ApproveDto;
import com.web.Bang.dto.ReportDto;
import com.web.Bang.model.Report;
import com.web.Bang.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReportService {
    @Transactional
    void reportReply(int replyId, ReportDto reportDto);

    @Transactional
    void reportReview(User user, int reviewId);

    @Transactional(readOnly = true)
    List<Report> getReportList(int reporterId);

    @Transactional(readOnly = true)
    List<Report> getAllReport();

    @Transactional
    Report setReportStatus(ApproveDto approveDto);
}
