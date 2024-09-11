package com.adama.sorho.redditClone.controller;

import com.adama.sorho.redditClone.dto.PostDto;
import com.adama.sorho.redditClone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api.prefix}/posts")
public class PostController {
	private final PostService postService;

	@PostMapping
	public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.create(postDto));
	}

	@GetMapping("/{id}")
	public PostDto find(@PathVariable Long id) {
		return this.postService.find(id);
	}

	@GetMapping
	public PagedModel<PostDto> findAll(@RequestParam int page, @RequestParam int size) {
		return new PagedModel<>(this.postService.findAll(PageRequest.of(page, size)));
	}

	@GetMapping("/subreddit/{subRedditId}")
	public PagedModel<PostDto> findBySubReddit(@PathVariable Long subRedditId,
											   @RequestParam int page, @RequestParam int size) {
		return new PagedModel<>(this.postService.findBySubReddit(subRedditId, PageRequest.of(page, size)));
	}

	@GetMapping("/user/{username}")
	public PagedModel<PostDto> findByUser(@PathVariable String username,
										  @RequestParam int page, @RequestParam int size) {
		return new PagedModel<>(this.postService.findByUser(username, PageRequest.of(page, size)));
	}
}
