package com.adama.sorho.redditClone.service.impl;

import com.adama.sorho.redditClone.model.User;
import com.adama.sorho.redditClone.service.JwtService;
import com.adama.sorho.redditClone.util.AppUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
	private final PrivateKey jwtSigningKey;
	private final PublicKey jwtVerificationKey;

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);

		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(jwtSigningKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	@Override
	public Boolean validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(jwtVerificationKey)
				.build()
				.parseClaimsJws(token);

		return true;
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	@Override
	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, user);
	}

	private String createToken(Map<String, Object> claims, User user) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + AppUtils.ACCESS_TOKEN_EXPIRE_IN))
				.signWith(jwtSigningKey, SignatureAlgorithm.RS256)
				.compact();
	}
}
