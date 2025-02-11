package com.KURUSH.KUFOREINER.member.exception;

import com.KURUSH.KUFOREINER.global.exception.HttpExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class MemberExistException extends RuntimeException{
    private final HttpStatus httpStatus;

    public MemberExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public MemberExistException() {
        this(HttpExceptionCode.MEMBER_EXISTS);
    }


}
