package com.KURUSH.KUFOREINER.global;

import lombok.Builder;

@Builder
public record LoginRequest(String userId, String password) {
}
