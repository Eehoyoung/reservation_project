package com.web.Bang.repository;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.dto.queryDslDto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {


    List<UserDto> findByRoleAndUserName(String role, String name);

    List<UserDto> findByUsernameContaining(String name);

    List<AdmintableDto> loadUserMonthTableCount();
}
