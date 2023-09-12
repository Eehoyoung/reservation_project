package com.web.Bang.dto.queryDslDto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.Bang.model.User;
import com.web.Bang.model.type.CSBoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CustomServiceBoardDto {

    private int id;

    private User user;

    private String title;

    private CSBoardType boardType;

    private int count;

    private String content;

    private Timestamp createTime;

    private int secret;

    @QueryProjection
    public CustomServiceBoardDto(int id, User user, String title, CSBoardType csBoardType, int count, String content, Timestamp createTime, int secret) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.boardType = csBoardType;
        this.count = count;
        this.content = content;
        this.createTime = createTime;
        this.secret = secret;
    }
}
