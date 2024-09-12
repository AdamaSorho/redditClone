package com.adama.sorho.redditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	private Long postId;
	private String text;
	private String username;
	private Instant createdOn;
}
