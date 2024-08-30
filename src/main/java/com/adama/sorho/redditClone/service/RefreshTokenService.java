package com.adama.sorho.redditClone.service;

import com.adama.sorho.redditClone.model.RefreshToken;

public interface RefreshTokenService {
	RefreshToken create(String username);
}
