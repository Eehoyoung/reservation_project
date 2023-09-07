package com.web.Bang.service;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.model.User;
import com.web.Bang.repository.UserRepository;
import com.web.Bang.repository.queryStorage.AdminTableQueryStorage;
import com.web.Bang.repository.queryStorage.QlrmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;
    private final QlrmRepository<AdmintableDto> qlrmRepository;
    private final AdminTableQueryStorage queryStorage;

    @Override
    @Modifying
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<AdmintableDto> loadHouseDtolist(String month, String string) {
        return qlrmRepository.returnDataList(queryStorage.findByMonthBestHouse(month, Integer.parseInt(string)), AdmintableDto.class);
    }

    @Override
    public List<AdmintableDto> loadAddressHouseCount() {
        return qlrmRepository.returnDataList(queryStorage.loadAddressHouseCount(), AdmintableDto.class);
    }

    @Override
    public List<AdmintableDto> loadMonthTableCount(String table) {
        return qlrmRepository.returnDataList(queryStorage.loadMonthTableCount(table), AdmintableDto.class);
    }

    @Override
    @Transactional
    public List<User> searchRoleAndUser(String role, String name) {
        List<User> users = userRepository.findByRoleAndUserName(role, name);
        return sortUserList(users);
    }

    @Override
    @Transactional
    public List<User> searchUserOnly(String name) {
        List<User> users = userRepository.findByUsernameContaining(name);
        return sortUserList(users);
    }

    private List<User> sortUserList(List<User> users) {
        users.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        return users;
    }
}
