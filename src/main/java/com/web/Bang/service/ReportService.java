package com.web.Bang.service;

import com.web.Bang.dto.ApproveDto;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Report;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import com.web.Bang.model.type.ReportType;
import com.web.Bang.repository.ReplyRepository;
import com.web.Bang.repository.ReportRepository;
import com.web.Bang.repository.ReviewRepository;
import com.web.Bang.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    private final ReplyRepository replyRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    public ReportService(ReportRepository reportRepository, ReplyRepository replyRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.replyRepository = replyRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void reportReply(User user, int replyId, Report report) {
        Reply replyEntity = replyRepository.findById(replyId).get();

        report.setReporter(user);
        report.setReportStatus(ReportType.RECEIVED);
        report.setReplyId(replyEntity);
        report.setRespondent(replyEntity.getReviewId().getHouseId().getHostId());

        reportRepository.save(report);
    }

    @Transactional
    public void reportReview(User user, int reviewId, Report report) {
        Review reviewEntity = reviewRepository.findById(reviewId).get();

        report.setReporter(user);
        report.setReportStatus(ReportType.RECEIVED);
        report.setReviewId(reviewEntity);
        report.setRespondent(reviewEntity.getGuestId());

        reportRepository.save(report);
    }

    @Transactional(readOnly = true)
    public List<Report> getReportList(int reporterId) {
        return reportRepository.findAllByReporter(reporterId);
    }

    @Transactional(readOnly = true)
    public List<Report> getAllReport() {
        return reportRepository.findAllOrderByIdDesc();

    }

    @Transactional
    public Report setReportStatus(ApproveDto approveDto) {
        System.out.println("service 확인 : " + approveDto.getApprove());
        Report reportEntity = reportRepository.findById(approveDto.getResId()).get();

        ReportType reportType = reportEntity.getReportStatus();

        switch (approveDto.getApprove()) {
            case "APPROVED":
                // 승인시 해당 회원의 신고횟수가 1 증가한다.
                reportType = ReportType.APPROVED;

                User userEntity = userRepository.findById(reportEntity.getRespondent().getId()).get();
                int reportCount = userEntity.getReportCount() + 1;
                userEntity.setReportCount(reportCount);

                break;
            case "CANCELED":
                reportType = ReportType.CANCELED;
                break;
        }

        reportEntity.setReportStatus(reportType);
        System.out.println("service 확인 : " + reportEntity);
        return reportEntity;
    }

}
