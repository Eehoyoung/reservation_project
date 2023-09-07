package com.web.Bang.dto;

import com.web.Bang.dto.kakao.Document;
import com.web.Bang.dto.kakao.Meta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoSearchDto {

    private Meta meta;
    private List<Document> documents = null;

}
