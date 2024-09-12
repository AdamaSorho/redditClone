package com.adama.sorho.redditClone.controller;

import com.adama.sorho.redditClone.dto.CommentDto;
import com.adama.sorho.redditClone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api.prefix}/comments")
public class CommentController {
	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.create(commentDto));
	}

	@GetMapping("/post/{postId}")
	public PagedModel<CommentDto> findByPost(@PathVariable Long postId, @RequestParam int page, @RequestParam int size) {
		return new PagedModel<CommentDto>(this.commentService.findByPost(postId, PageRequest.of(page, size)));
	}

	@GetMapping("/user/{username}")
	public PagedModel<CommentDto> findByUser(@PathVariable String username, @RequestParam int page, @RequestParam int size) {
		return new PagedModel<CommentDto>(this.commentService.findByUser(username, PageRequest.of(page, size)));
	}
}
