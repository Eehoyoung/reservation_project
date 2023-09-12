package com.web.Bang.service;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.UserDto;
import com.web.Bang.repository.HouseRepository;
import com.web.Bang.repository.ReservationRepository;
import com.web.Bang.repository.ReviewRepository;
import com.web.Bang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Modifying
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<AdmintableDto> loadHouseDtolist(String month, String string) {
        return houseRepository.findByMonthBestHouse(month, Integer.parseInt(string));
    }

    @Override
    public List<AdmintableDto> loadAddressHouseCount() {
        return houseRepository.loadAddressHouseCount();
    }

    @Override
    public List<AdmintableDto> loadMonthTableCount(String table) {
        List<AdmintableDto> list = new ArrayList<>();
        if (Objects.equals(table, "user")) {
            list = userRepository.loadUserMonthTableCount();
        } else if (Objects.equals(table, "house")) {
            list = houseRepository.loadHouseMonthTableCount();
        } else if (Objects.equals(table, "reservation")) {
            list = reservationRepository.loadReservationMonthTableCount();
        } else if (Objects.equals(table, "review")) {
            list = reviewRepository.loadReviewMonthTableCount();
        }
        return list;
    }

    @Override
    @Transactional
    public List<UserDto> searchRoleAndUser(String role, String name) {
        List<UserDto> users = userRepository.findByRoleAndUserName(role, name);
        return sortUserList(users);
    }

    @Override
    @Transactional
    public List<UserDto> searchUserOnly(String name) {
        List<UserDto> users = userRepository.findByUsernameContaining(name);
        return sortUserList(users);
    }

    private List<UserDto> sortUserList(List<UserDto> users) {
        users.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        return users;
    }
}
