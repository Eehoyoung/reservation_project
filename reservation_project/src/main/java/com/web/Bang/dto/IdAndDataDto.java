package com.web.Bang.dto;

public class IdAndDataDto<T> {
    int id;
    T data;

    public IdAndDataDto(int id, T data) {
        super();
        this.id = id;
        this.data = data;
    }


}
