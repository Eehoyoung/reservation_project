package com.web.Bang.repository;

import com.web.Bang.dto.queryDslDto.ReportQueryDto;

import java.util.List;

public interface ReportRepositoryCustom {


    List<ReportQueryDto> findAllByReporter(int reporterId);


    List<ReportQueryDto> findAllOrderByIdDesc();

}

