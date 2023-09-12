package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.Bang.model.Reply;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import com.web.Bang.model.type.ReportType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ReportQueryDto {

    private int id;

    private User reporter; // 신고자

    private User respondent; // 피신고자

    private String reportType; // 신고 유형

    private String detailText;

    private Review reviewId;

    private Reply replyId;

    private Timestamp creationDate;

    private ReportType reportStatus; // 신고 승인 여부

    @QueryProjection
    public ReportQueryDto(int id, User reporter, User respondent, String reportType, String detailText
            , Review reviewId, Reply replyId, Timestamp creationDate, ReportType reportStatus) {
        this.id = id;
        this.reporter = reporter;
        this.respondent = respondent;
        this.reportType = reportType;
        this.detailText = detailText;
        this.reviewId = reviewId;
        this.replyId = replyId;
        this.creationDate = creationDate;
        this.reportStatus = reportStatus;
    }
}
