package com.kethableez.walkerapi.Report.Model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "reports")
@Getter
@Setter
@RequiredArgsConstructor
public class Report {
    private String id;
    private String reportBody;
    private LocalDateTime reportDateTime;
    private ReportStatus status;
    private String reporterId;


    public Report(String reportBody, LocalDateTime reportDateTime, ReportStatus status, String reporterId) {
        this.reportBody = reportBody;
        this.reportDateTime = reportDateTime;
        this.status = status;
        this.reporterId = reporterId;
    }

}
