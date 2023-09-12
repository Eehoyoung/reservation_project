package com.web.Bang.service;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.UserDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {
    @Modifying
    @Transactional
    void deleteUser(int id);

    List<AdmintableDto> loadHouseDtolist(String month, String string);

    List<AdmintableDto> loadAddressHouseCount();

    List<AdmintableDto> loadMonthTableCount(String table);

    @Transactional
    List<UserDto> searchRoleAndUser(String role, String name);

    @Transactional
    List<UserDto> searchUserOnly(String name);
}
