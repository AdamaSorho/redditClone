package com.adama.sorho.redditClone.service;

import com.adama.sorho.redditClone.dto.request.LoginRequest;
import com.adama.sorho.redditClone.dto.request.RegisterRequest;
import com.adama.sorho.redditClone.dto.response.LoginResponse;

public interface AuthService {
	void signup(RegisterRequest registerRequest);

	void verifyAccount(String token);

	LoginResponse login(LoginRequest loginRequest);
}
