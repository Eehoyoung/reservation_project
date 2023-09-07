package com.web.Bang.auth.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sun.istack.NotNull;
import com.web.Bang.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final RedisService redisService;
    private final TokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT필터 진입");

        String servletPath = request.getServletPath();

        if (isSwaggerPath(servletPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtHeader = jwtTokenProvider.getTokenFromRequest(request);

        System.out.println("해더는?" + jwtHeader);

        // header 가 정상 비정상 ?
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // jwt 토큰을 검증 정상 사용자 확인
        String token = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "").trim();

        Boolean isTokenRevokedOrExpired = redisService.isTokenExpired(token); // Redis 저장된 토큰 확인

        if (isTokenRevokedOrExpired) { // 전환된 토큰이거나 토큰 만료된 경우
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "전환된 토큰 또는 만료된 토큰입니다.");
            return;
        }


        Long userCode = null;
        try {
            userCode = jwtTokenProvider.getUserIdFromToken(jwtHeader);

        } catch (TokenExpiredException e) {
            e.getStackTrace();
            request.setAttribute(JwtProperties.HEADER_STRING, "토큰 만료");
        } catch (JWTVerificationException e) {
            e.getStackTrace();
            request.setAttribute(JwtProperties.HEADER_STRING, "유효 않는 토큰.");
        }

        request.setAttribute("userCode", userCode);

        filterChain.doFilter(request, response);
    }

    private boolean isSwaggerPath(String path) {
        String[] swaggerPaths = {
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/v2/api-docs/**",
                "/webjars/**",
        };

        return Arrays.stream(swaggerPaths).anyMatch(path::startsWith);
    }
}