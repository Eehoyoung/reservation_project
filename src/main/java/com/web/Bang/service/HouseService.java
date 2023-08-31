package com.web.Bang.service;

import com.web.Bang.dto.RequestPostDto;
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
    List<House> getHouseList();

    @Transactional
    void addWishList(int houseId, User user);

    @Transactional
    List<House> getHouseListByAddress(String address, int houseId);

    @Transactional
    void deleteItemOfWishList(int houseId, int guestId);

    @Transactional
    List<House> findAllByHostId(int hostId);

    @Transactional
    void updateHouse(int houseId, RequestPostDto requestPostDto);

    @Transactional(readOnly = true)
    int getReviewCount(int houseId);

    @Transactional
    List<House> searchHouseByAddressAndType(String address, String type);

    @Transactional
    List<House> searchHouseByAddressOrType(String address, String type);

    @Transactional
    List<House> getHouseOrderByStarScore();

    @Transactional
    House findById(int houseId);

    @Transactional(readOnly = true)
    int getReviewCountByGuestId(int guestId);
}
