package com.adama.sorho.redditClone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
	@Id
	@GeneratedValue
	private Long id;
	private String token;
	private Instant expiryOn;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private User user;
}
