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
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	private String url;
	@Lob
	private String description;
	private Integer voteCount;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User user;
	@CreationTimestamp
	private Instant createdOn;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private SubReddit subReddit;
}
