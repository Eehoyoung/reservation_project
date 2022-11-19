package com.web.Bang.repository;

import com.web.Bang.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(value = "SELECT * FROM report WHERE reporter = ? ", nativeQuery = true)
    List<Report> findAllByReporter(int reporterId);

    @Query(value = "SELECT * FROM report ORDER BY id DESC ", nativeQuery = true)
    List<Report> findAllOrderByIdDesc();

}
