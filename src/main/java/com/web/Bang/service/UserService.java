package com.web.Bang.service;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    void saveUser(User user);

    @Transactional
    User updateUserInfo(User user);

    @Transactional
    User checkUsername(String username);

    User findByUserId(int id);

    @Transactional(readOnly = true)
    User getUserById(int userId);

    User getUser(String loginId);
}
