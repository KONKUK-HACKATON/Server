package com.KURUSH.KUFOREINER.global.exception.custom.security;

import com.KURUSH.KUFOREINER.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class BearerNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;
    public BearerNotFoundException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus=exceptionCode.getHttpStatus();
    }


    public BearerNotFoundException(){
        this(HttpExceptionCode.BEARER_NOT_FOUND);}
}
