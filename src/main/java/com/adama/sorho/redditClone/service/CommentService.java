package com.adama.sorho.redditClone.service;

import com.adama.sorho.redditClone.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
	CommentDto create(CommentDto commentDto);

	Page<CommentDto> findByPost(Long postId, Pageable pageable);

	Page<CommentDto> findByUser(String username, Pageable pageable);
}
