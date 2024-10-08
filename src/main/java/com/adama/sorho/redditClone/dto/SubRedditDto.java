package com.adama.sorho.redditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubRedditDto {
	private Long id;
	private String name;
	private String description;
	private Integer numberOfPosts;
}
