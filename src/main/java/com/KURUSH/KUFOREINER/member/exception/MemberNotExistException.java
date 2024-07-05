package com.KURUSH.KUFOREINER.member.exception;

import com.KURUSH.KUFOREINER.global.exception.HttpExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberNotExistException extends RuntimeException {
    private final HttpStatus httpStatus;

    public MemberNotExistException(HttpExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public MemberNotExistException() {
        this(HttpExceptionCode.MEMBER_NOT_EXISTS);
    }


}
