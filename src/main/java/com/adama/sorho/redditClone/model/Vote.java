package com.adama.sorho.redditClone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
	@Id
	@GeneratedValue
	private Long id;
	private VoteType type;
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
