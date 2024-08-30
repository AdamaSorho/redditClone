package com.adama.sorho.redditClone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubReddit {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Post> posts;
	@CreationTimestamp
	private Instant createdOn;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
