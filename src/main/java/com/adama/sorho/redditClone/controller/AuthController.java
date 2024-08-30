package com.adama.sorho.redditClone.controller;

import com.adama.sorho.redditClone.dto.request.LoginRequest;
import com.adama.sorho.redditClone.dto.request.RegisterRequest;
import com.adama.sorho.redditClone.dto.response.LoginResponse;
import com.adama.sorho.redditClone.dto.response.MessageResponse;
import com.adama.sorho.redditClone.service.AuthService;
import com.adama.sorho.redditClone.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.api.prefix}/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public MessageResponse signup(@RequestBody RegisterRequest registerRequest) {
		System.out.println("Okay");
		authService.signup(registerRequest);

		return new MessageResponse(AppUtils.USER_REGISTERED_SUCCESSFULLY);
	}

	@GetMapping("/accountVerification/{token}")
	public MessageResponse verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);

		return new MessageResponse(AppUtils.ACCOUNT_ACTIVATED_SUCCESS);
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
