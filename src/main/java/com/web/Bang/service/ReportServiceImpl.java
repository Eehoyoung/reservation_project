package com.web.Bang.service;

import com.web.Bang.dto.ApproveDto;
import com.web.Bang.dto.ReportDto;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Report;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import com.web.Bang.model.type.ReportType;
import com.web.Bang.repository.ReplyRepository;
import com.web.Bang.repository.ReportRepository;
import com.web.Bang.repository.ReviewRepository;
import com.web.Bang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    private final ReplyRepository replyRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void reportReply(int replyId, ReportDto reportDto) {
        Reply replyEntity = replyRepository.findById(replyId).orElseThrow(
                () -> new RuntimeException("해당 댓글은 존재하지 않습니다.")
        );
        Report report = new Report();
        report.setReporter(reportDto.getReporter());
        report.setReportStatus(ReportType.RECEIVED);
        report.setReplyId(replyEntity);
        report.setRespondent(replyEntity.getReviewId().getHouseId().getHostId());

        reportRepository.save(report);
    }

    @Override
    @Transactional
    public void reportReview(User user, int reviewId) {
        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(
                () -> new RuntimeException("해당 리뷰는 존재하지 않습니다.")
        );
        Report report = new Report();
        report.setReporter(user);
        report.setReportStatus(ReportType.RECEIVED);
        report.setReviewId(reviewEntity);
        report.setRespondent(reviewEntity.getGuestId());

        reportRepository.save(report);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Report> getReportList(int reporterId) {
        return reportRepository.findAllByReporter(reporterId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Report> getAllReport() {
        return reportRepository.findAllOrderByIdDesc();

    }

    @Override
    @Transactional
    public Report setReportStatus(ApproveDto approveDto) {
        Report reportEntity = reportRepository.findById(approveDto.getResId()).orElseThrow(
                () -> new RuntimeException("해당 신고 내역은 존재하지 않습니다.")
        );

        ReportType reportType = reportEntity.getReportStatus();

        if(approveDto.getApprove().equals("APPROVED")){
            // 승인시 해당 회원의 신고횟수가 1 증가한다.
            reportType = ReportType.APPROVED;

            User userEntity = userRepository.findById(reportEntity.getRespondent().getId()).orElseThrow(
                    () -> new RuntimeException("사용자가 존재 하지 않습니다.")
            );

            int reportCount = userEntity.getReportCount() + 1;

            userEntity.setReportCount(reportCount);

        } else if (approveDto.getApprove().equals("CANCELED")) {

            reportType = ReportType.CANCELED;

        }
        reportEntity.setReportStatus(reportType);
        return reportEntity;
    }

}
