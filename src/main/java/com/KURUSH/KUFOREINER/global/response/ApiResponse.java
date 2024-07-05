package com.KURUSH.KUFOREINER.global.response;

public record ApiResponse<T>(int staus, T results) {
}
