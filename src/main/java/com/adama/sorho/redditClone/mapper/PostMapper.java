package com.adama.sorho.redditClone.mapper;

import com.adama.sorho.redditClone.dto.PostDto;
import com.adama.sorho.redditClone.model.Post;
import com.adama.sorho.redditClone.model.SubReddit;
import com.adama.sorho.redditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
	@Mapping(target = "description", source = "postDto.description")
	@Mapping(target = "name", source = "postDto.name")
	@Mapping(target = "user", source = "user")
	@Mapping(target = "subReddit", source = "subReddit")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	Post map(PostDto postDto, SubReddit subReddit, User user);

	@Mapping(target = "subRedditName", source = "subReddit.name")
	@Mapping(target = "username", source = "user.username")
	PostDto mapToDto(Post post);
}
