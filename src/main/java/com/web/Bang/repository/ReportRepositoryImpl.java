package com.web.Bang.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.Bang.dto.queryDslDto.QReportQueryDto;
import com.web.Bang.dto.queryDslDto.ReportQueryDto;
import com.web.Bang.model.QReport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReportRepositoryImpl implements ReportRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ReportRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReportQueryDto> findAllByReporter(int reporterId) {
        return queryFactory
                .select(new QReportQueryDto(
                        QReport.report.id,
                        QReport.report.reporter,
                        QReport.report.respondent,
                        QReport.report.reportType,
                        QReport.report.detailText,
                        QReport.report.reviewId,
                        QReport.report.replyId,
                        QReport.report.creationDate,
                        QReport.report.reportStatus
                ))
                .from(QReport.report)
                .where(QReport.report.reporter.id.eq(reporterId))
                .fetch();
    }

    @Override
    public List<ReportQueryDto> findAllOrderByIdDesc() {
        return queryFactory
                .select(new QReportQueryDto(
                        QReport.report.id,
                        QReport.report.reporter,
                        QReport.report.respondent,
                        QReport.report.reportType,
                        QReport.report.detailText,
                        QReport.report.reviewId,
                        QReport.report.replyId,
                        QReport.report.creationDate,
                        QReport.report.reportStatus
                ))
                .from(QReport.report)
                .orderBy(QReport.report.id.desc())
                .fetch();
    }
}
