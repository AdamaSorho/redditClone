package com.adama.sorho.redditClone.adapter;

import com.adama.sorho.redditClone.dto.SubRedditDto;
import com.adama.sorho.redditClone.model.SubReddit;

public class SubRedditAdapter {

	public static SubReddit getSubRedditFromSubRedditDto(SubRedditDto subRedditDto) {
		return SubReddit.builder()
				.name(subRedditDto.getName())
				.description(subRedditDto.getDescription())
				.build();
	}

	public static SubRedditDto getSubRedditDtoFromSubReddit(SubReddit subReddit) {
		return SubRedditDto.builder()
				.id(subReddit.getId())
				.name(subReddit.getName())
				.description(subReddit.getDescription())
				.numberOfPosts(subReddit.getPosts().size())
				.build();
	}
}
