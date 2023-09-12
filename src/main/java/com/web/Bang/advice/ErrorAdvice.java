package com.web.Bang.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(value = Exception.class)
    public String showError(Exception e) {
        e.printStackTrace();
        return "/advice/errorPage";
    }
}
