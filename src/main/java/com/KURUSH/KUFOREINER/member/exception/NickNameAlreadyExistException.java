package com.KURUSH.KUFOREINER.member.exception;

import com.KURUSH.KUFOREINER.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NickNameAlreadyExistException extends RuntimeException{
    private final HttpStatus httpStatus;

    public NickNameAlreadyExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public NickNameAlreadyExistException() {
        this(HttpExceptionCode.NICKNAME_EXIST);
    }

}
