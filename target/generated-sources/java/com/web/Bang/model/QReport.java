package com.web.Bang.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = -1248903480L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final DateTimePath<java.sql.Timestamp> creationDate = createDateTime("creationDate", java.sql.Timestamp.class);

    public final StringPath detailText = createString("detailText");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QReply replyId;

    public final QUser reporter;

    public final EnumPath<com.web.Bang.model.type.ReportType> reportStatus = createEnum("reportStatus", com.web.Bang.model.type.ReportType.class);

    public final StringPath reportType = createString("reportType");

    public final QUser respondent;

    public final QReview reviewId;

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.replyId = inits.isInitialized("replyId") ? new QReply(forProperty("replyId"), inits.get("replyId")) : null;
        this.reporter = inits.isInitialized("reporter") ? new QUser(forProperty("reporter")) : null;
        this.respondent = inits.isInitialized("respondent") ? new QUser(forProperty("respondent")) : null;
        this.reviewId = inits.isInitialized("reviewId") ? new QReview(forProperty("reviewId"), inits.get("reviewId")) : null;
    }

}

