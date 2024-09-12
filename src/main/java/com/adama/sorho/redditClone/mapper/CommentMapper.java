package com.adama.sorho.redditClone.mapper;

import com.adama.sorho.redditClone.dto.CommentDto;
import com.adama.sorho.redditClone.model.Comment;
import com.adama.sorho.redditClone.model.Post;
import com.adama.sorho.redditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "post", source = "post")
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "user", source = "user")
	Comment map(CommentDto commentDto, Post post, User user);

	@Mapping(target = "postId", expression = "java(comment.getPost().getId())")
	@Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
	CommentDto mapToDto(Comment comment);
}
