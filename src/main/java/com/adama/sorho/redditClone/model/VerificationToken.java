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
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
	@Id
	@GeneratedValue
	private Long id;
	private String token;
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	private Instant expiryOn;
}
