package com.adama.sorho.redditClone.dto.response;

public record LoginResponse (String accessToken, String refreshToken, int expireOn) {
}
