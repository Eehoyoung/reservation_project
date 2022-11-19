package com.web.Bang.dto;

import com.web.Bang.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostDto {

    private MultipartFile file;
    private String name;
    private String address;
    private String detailAddress;
    private String type;
    private int capacity;
    private int OneDayPrice;
    private String infoText;

    public Image toEntity(String imageUrl) {
        return Image.builder().imageUrl(imageUrl).originFileName(file.getOriginalFilename()).build();
    }
}
