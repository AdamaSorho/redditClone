package com.adama.sorho.redditClone.service.impl;

import com.adama.sorho.redditClone.dto.request.LoginRequest;
import com.adama.sorho.redditClone.dto.request.RegisterRequest;
import com.adama.sorho.redditClone.dto.response.LoginResponse;
import com.adama.sorho.redditClone.exception.SpringRedditException;
import com.adama.sorho.redditClone.integration.EmailSender;
import com.adama.sorho.redditClone.model.NotificationEmail;
import com.adama.sorho.redditClone.model.RefreshToken;
import com.adama.sorho.redditClone.model.User;
import com.adama.sorho.redditClone.model.VerificationToken;
import com.adama.sorho.redditClone.repository.UserRepository;
import com.adama.sorho.redditClone.repository.VerificationTokenRepository;
import com.adama.sorho.redditClone.service.AuthService;
import com.adama.sorho.redditClone.service.JwtService;
import com.adama.sorho.redditClone.service.RefreshTokenService;
import com.adama.sorho.redditClone.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailSender emailSender;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final RefreshTokenService refreshTokenService;

	@Override
	public void signup(RegisterRequest registerRequest) {
		User user = User.builder()
				.username(registerRequest.username())
				.email(registerRequest.email())
				.password(passwordEncoder.encode(registerRequest.password()))
				.enabled(false)
				.build();

		userRepository.save(user);
		String token = generateVerificationToken(user);
		emailSender.send(NotificationEmail.builder()
						.subject(AppUtils.ACTIVATION_EMAIL_SUBJECT)
						.recipient(user.getEmail())
						.body(AppUtils.activationEmailBody(token))
				.build());
	}

	@Override
	public void verifyAccount(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new SpringRedditException(AppUtils.INVALID_TOKEN));
		fetchUserAndEnable(verificationToken);
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.username(), loginRequest.password()));
		if (!authentication.isAuthenticated()) {
			throw new SpringRedditException(AppUtils.USER_NOT_FOUND);
		}
		RefreshToken refreshToken = refreshTokenService.create(loginRequest.username());

		return new LoginResponse(jwtService.generateToken(refreshToken.getUser()), refreshToken.getToken(),
				AppUtils.ACCESS_TOKEN_EXPIRE_IN);
	}

	@Override
	public User currentUser() {
		org.springframework.security.core.userdetails.User userDetails =
				(org.springframework.security.core.userdetails.User) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();

		return this.userRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new SpringRedditException(AppUtils.notFindException("user")));
	}

	private void fetchUserAndEnable(VerificationToken verificationToken) {
		User user = verificationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
	}


	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = VerificationToken.builder()
				.token(token)
				.user(user)
				.build();

		verificationTokenRepository.save(verificationToken);

		return token;
	}
}
