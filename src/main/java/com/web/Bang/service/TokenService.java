package com.web.Bang.service;

import com.sun.istack.NotNull;
import com.web.Bang.auth.jwt.JwtProperties;
import com.web.Bang.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserServiceImpl userService;

    public Cookie createJwtCookie(@NotNull User authenticatedUser, String jwtToken) {
        UserDetails userDetails = this.userService.loadUserByUsername(authenticatedUser.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        jwtToken = JwtProperties.TOKEN_PREFIX.trim() + jwtToken;
        Cookie jwtCookie = new Cookie("Authorization", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
        jwtCookie.setPath("/");

        return jwtCookie;
    }
}
