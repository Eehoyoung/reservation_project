package com.web.Bang.dto;

import com.web.Bang.model.Reply;
import com.web.Bang.model.Review;
import com.web.Bang.model.User;
import com.web.Bang.model.type.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private int id;

    private User reporter; // 신고자

    private User respondent; // 피신고자

    private String reportType; // 신고 유형

    private String detailText;

    private Review reviewId;

    private Reply replyId;

    private Timestamp creationDate;

    private ReportType reportStatus; // 신고 승인 여부


}
