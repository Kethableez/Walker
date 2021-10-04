package com.kethableez.walkerapi.Report.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.kethableez.walkerapi.Report.Model.Report;
import com.kethableez.walkerapi.Report.Model.ReportStatus;
import com.kethableez.walkerapi.Report.Repository.ReportRepository;
import com.kethableez.walkerapi.Utility.Response.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    private ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ActionResponse createReport(String reportBody, String reporterId) {
        Report report = new Report(reportBody, LocalDateTime.now(), ReportStatus.NEW, reporterId);
        reportRepository.save(report);
        return new ActionResponse(true, "Dodano zgłoszenie");
    }

    public List<Report> getReportsByStatus(ReportStatus status) {
        return reportRepository.findAllByStatus(status);
    }

    public ActionResponse changeReportStatus(String reportId, ReportStatus status) {
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent()) {
            report.get().setStatus(status);
            reportRepository.save(report.get());
            return new ActionResponse(true, "Zmieniono status zgłoszenia");
        }
        else return new ActionResponse(false, "Nie ma takiego zgłoszenia");
    }
}
