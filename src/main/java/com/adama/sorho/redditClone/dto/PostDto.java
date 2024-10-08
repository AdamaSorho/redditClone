package com.adama.sorho.redditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private Long id;
	private String name;
	private String url;
	private String description;
	private String username;
	private String subRedditName;
}
