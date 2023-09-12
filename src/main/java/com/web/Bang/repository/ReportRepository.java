package com.web.Bang.repository;

import com.web.Bang.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer>, ReportRepositoryCustom {
}
