package com.web.Bang.controller.apicontroller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.ResponseDto;
import com.web.Bang.model.User;
import com.web.Bang.model.type.RoleType;
import com.web.Bang.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;

    @PutMapping("/user/update")
    public ResponseDto<Integer> updateUserInfo(@RequestBody User user) {

        User newUser = userService.updateUserInfo(user);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(newUser.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/user/username-check")
    public ResponseDto<User> joinCheck(@RequestBody User user) {
        User userEntity = userService.checkUsername(user.getUsername());
        return new ResponseDto<>(HttpStatus.OK.value(), userEntity);
    }

    @GetMapping("/guest/be-host")
    public String beHost(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        principalDetail.getUser().setRole(RoleType.HOST);
        Collection<GrantedAuthority> collectors = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        collectors.forEach((element) -> element.getAuthority().replace("GUEST", "HOST"));

        return "<script>location.href='/user/behost'</script>";

    }

    @GetMapping("/host/be-guest")
    public String beguest(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        principalDetail.getUser().setRole(RoleType.GUEST);

        Collection<GrantedAuthority> collectors = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        collectors.forEach((element) -> element.getAuthority().replace("HOST", "GUEST"));
        return "<script>location.href='/user/beguest'</script>";
    }
}
