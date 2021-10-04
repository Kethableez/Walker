package com.kethableez.walkerapi.Report.Repository;

import java.util.List;

import com.kethableez.walkerapi.Report.Model.Report;
import com.kethableez.walkerapi.Report.Model.ReportStatus;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findAllByStatus(ReportStatus status);
}
