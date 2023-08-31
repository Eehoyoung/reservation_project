package com.web.Bang.model;

import com.web.Bang.model.type.ReportType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reporter")
    private User reporter; // 신고자

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "respondent")
    private User respondent; // 피신고자

    @Column(nullable = false)
    private String reportType; // 신고 유형

    @Lob
    private String detailText;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reviewId")
    private Review reviewId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "replyId")
    private Reply replyId;

    @CreationTimestamp
    private Timestamp creationDate;

    @Enumerated(EnumType.STRING)
    private ReportType reportStatus; // 신고 승인 여부

}
