package com.web.Bang.controller;

import com.web.Bang.auth.jwt.TokenProvider;
import com.web.Bang.model.User;
import com.web.Bang.service.KakaoAuthService;
import com.web.Bang.service.TokenService;
import com.web.Bang.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final KakaoAuthService kakaoAuthService;
    private final UserServiceImpl userService;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

    @GetMapping("/auth/login_form")
    public String loginForm() {
        return "user/login_form";
    }

    @GetMapping("/auth/join_form")
    public String joinForm(User user) {
        return "user/join_form";
    }

    @GetMapping("/user/join-complete/{userId}")
    public String completeJoinForm(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user/join_complete_form";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            tokenService.logoutToken(request, response);
        }
        return "redirect:/";
    }

    @PostMapping("/auth/joinProc")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/user/join-complete/" + user.getId();
    }

    @GetMapping("/auth/kakao/login_proc")
    public String kakaoCallback(@RequestParam String code, HttpServletResponse response) {
        String redirectUrl = "http://localhost:9090/auth/kakao/login_proc";
        String key = "99464c317cd7882fee923fe9ebcfdafb";
        try {
            String accessToken = kakaoAuthService.getAccessToken(code, redirectUrl, key);
            User authenticationUser = kakaoAuthService.getAuthenticatedUser(accessToken);
            String jwtToken = tokenProvider.createToken(authenticationUser);
            Cookie jwtCookie = tokenService.createJwtCookie(authenticationUser, jwtToken);
            response.addCookie(jwtCookie);

            if (authenticationUser.getPhoneNumber().equals("0000000000")) {
                return "redirect:/user/update_form";
            }

            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/user/error";
        }
    }

    @PostMapping("/auth/loginProc")
    public String loginProc(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletResponse response) {

        boolean flag = userService.loginService(username, password);

        if (!flag) {
            return "redirect:/auth/login_form";
        }

        User authenticatedUser = userService.getUser(username);

        final String JwtToken = tokenProvider.createToken(authenticatedUser);

        Cookie jwtCookie = tokenService.createJwtCookie(authenticatedUser, JwtToken);

        response.addCookie(jwtCookie);

        return "redirect:/";

    }
}
