package com.web.Bang.service;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.model.User;
import com.web.Bang.model.type.LoginType;
import com.web.Bang.model.type.RoleType;
import com.web.Bang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void saveUser(User user) {
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
        }
    }

    @Override
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

    @Override
    @Transactional
    public User checkUsername(String username) {

        return userRepository.findByUsername(username).orElseGet(User::new);
    }

    @Override
    public User findByUserId(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재 하지 않는 사용자 입니다.")
        );
    }

    private String changePhoneNumFormat(String phoneNum) {
        if (phoneNum.length() != 11) {
            return phoneNum;
        }
        return phoneNum.substring(0, 3) + "-" + phoneNum.subSequence(3, 7) + "-"
                + phoneNum.subSequence(7, 11);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재 하지 않는 사용자 입니다.")
        );
    }

    @Override
    public User getUser(String loginId) {
        return userRepository.findByUsername(loginId).orElseThrow(
                () -> new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다.")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당유저를 찾을 수 없습니다."));
        return new PrincipalDetail(principal);
    }

    @Transactional
    public boolean loginService(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("해당 유저를 찾을 수 없습니다.")
        );
        return user != null && bCryptPasswordEncoder.matches(password, user.getPassword());
    }
}