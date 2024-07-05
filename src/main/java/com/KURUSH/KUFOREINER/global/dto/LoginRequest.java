package com.KURUSH.KUFOREINER.global.dto;

import lombok.Builder;

@Builder
public record LoginRequest(String username, String password) {
}
