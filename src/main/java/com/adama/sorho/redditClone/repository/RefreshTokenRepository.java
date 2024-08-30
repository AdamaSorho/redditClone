package com.adama.sorho.redditClone.repository;

import com.adama.sorho.redditClone.model.RefreshToken;
import com.adama.sorho.redditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByUser(User user);
}
