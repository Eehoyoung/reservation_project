package com.web.Bang.service;

import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.model.User;
import com.web.Bang.model.type.LoginType;
import com.web.Bang.model.type.RoleType;
import com.web.Bang.repository.UserRepository;
import com.web.Bang.repository.queryStorage.AdminTableQueryStorage;
import com.web.Bang.repository.queryStorage.QlrmRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final QlrmRepository<AdmintableDto> qlrmRepository;

    private final AdminTableQueryStorage queryStorage;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, QlrmRepository<AdmintableDto> qlrmRepository, AdminTableQueryStorage queryStorage, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.qlrmRepository = qlrmRepository;
        this.queryStorage = queryStorage;
        this.encoder = encoder;
    }

    @Transactional
    public int saveUser(User user) {
        try {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            user.setPhoneNumber(changePhoneNumFormat(user.getPhoneNumber()));
            user.setPassword(encPassword);
            if (!user.getUsername().startsWith("kakao_")) {
                user.setLoginType(LoginType.ORIGIN);
            }
            user.setRole(RoleType.GUEST);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Transactional
    public User updateUserInfo(User user) {

        User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        System.out.println("update : " + userEntity);
        if (userEntity.getLoginType() != LoginType.KAKAO) {
            String rawPassword = user.getPassword();
            String hashPassword = encoder.encode(rawPassword);
            userEntity.setPassword(hashPassword);
        }
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(changePhoneNumFormat(user.getPhoneNumber()));
        return userEntity;

    }

    @Transactional
    public User checkUsername(String username) {

        return userRepository.findByUsername(username).orElseGet(User::new);
    }

    public User findByUserId(int id) {
        return userRepository.findById(id).get();
    }

    private String changePhoneNumFormat(String phoneNum) {
        if (phoneNum.length() != 11) {
            return phoneNum;
        }
        return phoneNum.substring(0, 3) + "-" + phoneNum.subSequence(3, 7) + "-"
                + phoneNum.subSequence(7, 11);
    }

    @Transactional
    public List<User> showAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public List<User> searchRoleAndUser(String role, String name) {
        List<User> users = userRepository.findByRoleAndUserName(role, name);
        return sortUserList(users);
    }

    @Transactional
    public List<User> searchUserOnly(String name) {
        List<User> users = userRepository.findByUsernameContaining(name);
        return sortUserList(users);
    }

    private List<User> sortUserList(List<User> users) {
        users.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        return users;
    }

    public List<AdmintableDto> loadHouseDtolist(String month, String string) {
        return qlrmRepository.returnDataList(queryStorage.findByMonthBestHouse(month, Integer.parseInt(string)), AdmintableDto.class);
    }

    public List<AdmintableDto> loadAddressHouseCount() {
        return qlrmRepository.returnDataList(queryStorage.loadAddressHouseCount(), AdmintableDto.class);
    }

    public List<AdmintableDto> loadMonthTableCount(String table) {
        return qlrmRepository.returnDataList(queryStorage.loadMonthTableCount(table), AdmintableDto.class);
    }

    @Transactional(readOnly = true)
    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Modifying
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}