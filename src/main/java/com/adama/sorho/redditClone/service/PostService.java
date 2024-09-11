package com.adama.sorho.redditClone.service;

import com.adama.sorho.redditClone.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
	PostDto find(Long id);

	Page<PostDto> findAll(Pageable pageable);

	Page<PostDto> findBySubReddit(Long subRedditId, Pageable pageable);

	Page<PostDto> findByUser(String username, Pageable pageable);

	PostDto create(PostDto postDto);
}
