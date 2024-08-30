package com.adama.sorho.redditClone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	@CreationTimestamp
	private Instant createdOn;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
