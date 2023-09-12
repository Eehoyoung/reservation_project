package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.web.Bang.dto.queryDslDto.QReportQueryDto is a Querydsl Projection type for ReportQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReportQueryDto extends ConstructorExpression<ReportQueryDto> {

    private static final long serialVersionUID = -515399475L;

    public QReportQueryDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> reporter, com.querydsl.core.types.Expression<? extends com.web.Bang.model.User> respondent, com.querydsl.core.types.Expression<String> reportType, com.querydsl.core.types.Expression<String> detailText, com.querydsl.core.types.Expression<? extends com.web.Bang.model.Review> reviewId, com.querydsl.core.types.Expression<? extends com.web.Bang.model.Reply> replyId, com.querydsl.core.types.Expression<? extends java.sql.Timestamp> creationDate, com.querydsl.core.types.Expression<com.web.Bang.model.type.ReportType> reportStatus) {
        super(ReportQueryDto.class, new Class<?>[]{int.class, com.web.Bang.model.User.class, com.web.Bang.model.User.class, String.class, String.class, com.web.Bang.model.Review.class, com.web.Bang.model.Reply.class, java.sql.Timestamp.class, com.web.Bang.model.type.ReportType.class}, id, reporter, respondent, reportType, detailText, reviewId, replyId, creationDate, reportStatus);
    }

}

