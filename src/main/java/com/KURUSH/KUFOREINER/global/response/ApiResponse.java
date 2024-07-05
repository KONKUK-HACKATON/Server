package com.KURUSH.KUFOREINER.global.response;

public record ApiResponse<T>(String success, T data) {
}
