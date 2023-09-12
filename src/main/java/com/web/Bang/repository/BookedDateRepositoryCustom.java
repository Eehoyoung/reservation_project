package com.web.Bang.repository;

import com.web.Bang.dto.queryDslDto.BookedDateDto;

import java.util.List;

public interface BookedDateRepositoryCustom {
    List<BookedDateDto> findAllByHouseId(int houseId);
}
