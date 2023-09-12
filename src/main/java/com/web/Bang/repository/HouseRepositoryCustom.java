package com.web.Bang.repository;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.HouseDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HouseRepositoryCustom {

    // 현재 조회 중인 숙소 제외 같은 지역 숙소 리스트 별점 높은 순으로 가져오기
    Optional<List<HouseDto>> findAllByAddress(String address, int houseId);

    List<HouseDto> findAllByHostId(@Param(value = "hostId") int hostId);

    List<HouseDto> findAllByAddressAndTypeOrderByIdDesc(String address, String type);

    List<HouseDto> findAllByAddressOrTypeOrderByIdDesc(String address, String type);

    List<HouseDto> findAllByStarScore();

    List<HouseDto> findAllHouse();

    List<AdmintableDto> findByMonthBestHouse(String month, int limit);

    List<AdmintableDto> loadHouseMonthTableCount();

    List<AdmintableDto> loadAddressHouseCount();
}
