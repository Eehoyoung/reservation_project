package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.Bang.model.type.LoginType;
import com.web.Bang.model.type.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private Timestamp creationDate;

    private RoleType role;

    private LoginType loginType;

    private int reportCount;

    @QueryProjection
    public UserDto(int id, String username, String password, String email, String phoneNumber, Timestamp creationDate,
                   RoleType role, LoginType loginType, int reportCount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.creationDate = creationDate;
        this.role = role;
        this.loginType = loginType;
        this.reportCount = reportCount;
    }
}
