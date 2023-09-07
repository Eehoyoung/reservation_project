package com.web.Bang.service;

import com.web.Bang.dto.kakao.KakaoProfile;
import com.web.Bang.dto.kakao.OAuthToken;
import com.web.Bang.model.User;
import com.web.Bang.model.type.LoginType;
import com.web.Bang.model.type.RoleType;
import com.web.Bang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String getAccessToken(String code, String redirectUrl, String key) {
        OAuthToken oAuthToken = getOAuthToken(code, redirectUrl, key);
        return oAuthToken.getAccessToken();
    }

    private OAuthToken getOAuthToken(String code, String redirectUrl, String key) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        String grantType = "authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("grant_type", grantType);
        map.add("client_id", key);
        map.add("redirect_uri", redirectUrl);
        map.add("code", code);

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<OAuthToken> responseEntity = restTemplate.postForEntity(tokenUrl, request, OAuthToken.class);

        return responseEntity.getBody();
    }

    public User getAuthenticatedUser(String accessToken) {
        KakaoProfile kakaoProfile = getKakaoProfile(accessToken);
        return saveKakaoUserInfo(kakaoProfile);
    }

    public KakaoProfile getKakaoProfile(String accessToken) {
        String profileUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<KakaoProfile> responseEntity = restTemplate.exchange(profileUrl, HttpMethod.GET, requestEntity, KakaoProfile.class);

        return responseEntity.getBody();
    }

    public User saveKakaoUserInfo(KakaoProfile kakaoProfile) {

        String randomStr = UUID.randomUUID().toString();
        String kakaoPassword = encoder.encode(randomStr);

        KakaoProfile.KakaoAccount kakaoAccount = kakaoProfile.getKakaoAccount();
        Long kakaoId = kakaoProfile.getId();

        Optional<User> optionalUser = userRepository.findByUsername(String.valueOf(kakaoId)); //이미 가입된 사용자인지 확인

        //이미 가입된 사용자라면 리턴
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }


        User newUser = new User();
        newUser.setUsername(String.valueOf(kakaoId));
        newUser.setPassword("12345678");
        newUser.setEmail(kakaoAccount.getEmail());
        newUser.setLoginType(LoginType.KAKAO);
        newUser.setPhoneNumber("0000000000");
        newUser.setRole(RoleType.GUEST);

        return userRepository.save(newUser);
    }
}
