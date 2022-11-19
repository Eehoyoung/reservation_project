package com.web.Bang.advice;

import com.web.Bang.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> error(Exception e) {
        return new ResponseDto<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
