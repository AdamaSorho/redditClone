package com.adama.sorho.redditClone.service;

import java.util.Date;
import java.util.function.Function;

import com.adama.sorho.redditClone.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	String extractUsername(String token);
	Date extractExpiration(String token);
	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);
	Boolean validateToken(String token, UserDetails userDetails);
	String generateToken(User user);
}
