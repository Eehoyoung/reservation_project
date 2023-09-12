package com.web.Bang.service;

import com.web.Bang.dto.RequestPostDto;
import com.web.Bang.dto.queryDslDto.HouseDto;
import com.web.Bang.model.House;
import com.web.Bang.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HouseService {
    @Transactional
    House getHouseDetail(int houseId);

    @Transactional
    @Modifying
    void deleteHouse(int houseId);

    @Transactional
    void postHouse(RequestPostDto requestPostDto, User user);

    @Transactional
    List<HouseDto> getHouseList();

    @Transactional
    void addWishList(int houseId, User user);

    @Transactional
    List<HouseDto> getHouseListByAddress(String address, int houseId);

    @Transactional
    void deleteItemOfWishList(int houseId, int guestId);

    @Transactional
    List<HouseDto> findAllByHostId(int hostId);

    @Transactional
    void updateHouse(int houseId, RequestPostDto requestPostDto);

    @Transactional(readOnly = true)
    int getReviewCount(int houseId);

    @Transactional
    List<HouseDto> searchHouseByAddressAndType(String address, String type);

    @Transactional
    List<HouseDto> searchHouseByAddressOrType(String address, String type);

    @Transactional
    List<HouseDto> getHouseOrderByStarScore();

    @Transactional
    House findById(int houseId);

    @Transactional(readOnly = true)
    int getReviewCountByGuestId(int guestId);
}
