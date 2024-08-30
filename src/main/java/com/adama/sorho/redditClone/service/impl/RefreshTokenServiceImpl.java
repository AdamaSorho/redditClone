package com.adama.sorho.redditClone.service.impl;

import com.adama.sorho.redditClone.exception.SpringRedditException;
import com.adama.sorho.redditClone.model.RefreshToken;
import com.adama.sorho.redditClone.model.User;
import com.adama.sorho.redditClone.repository.RefreshTokenRepository;
import com.adama.sorho.redditClone.repository.UserRepository;
import com.adama.sorho.redditClone.service.RefreshTokenService;
import com.adama.sorho.redditClone.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public RefreshToken create(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException(AppUtils.USER_NOT_FOUND));
		refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

		return refreshTokenRepository.save(RefreshToken.builder()
						.token(UUID.randomUUID().toString())
						.user(user)
						.expiryOn(Instant.now().plusMillis(AppUtils.REFRESH_TOKEN_EXPIRE_ON))
				.build());
	}
}
