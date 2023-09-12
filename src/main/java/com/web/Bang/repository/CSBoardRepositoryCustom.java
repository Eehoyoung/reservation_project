package com.web.Bang.repository;

import com.web.Bang.dto.queryDslDto.CustomServiceBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CSBoardRepositoryCustom {

    Page<CustomServiceBoardDto> findByTitleContaining(String title, Pageable pageable);

    List<CustomServiceBoardDto> loadNoticeBoard();
}


