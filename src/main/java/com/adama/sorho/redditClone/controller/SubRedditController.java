package com.adama.sorho.redditClone.controller;

import com.adama.sorho.redditClone.dto.SubRedditDto;
import com.adama.sorho.redditClone.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.api.prefix}/subreddit")
public class SubRedditController {
	private SubRedditService subRedditService;

	@PostMapping
	public ResponseEntity<SubRedditDto> create(@RequestBody SubRedditDto subRedditDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(subRedditService.create(subRedditDto));
	}

	@GetMapping
	public PagedModel<SubRedditDto> findAll(@RequestParam int page, @RequestParam int size) {
		return new PagedModel<>(subRedditService.findAll(PageRequest.of(page, size)));
	}

	@GetMapping("/{id}")
	public SubRedditDto find(@PathVariable Long id) {
		return subRedditService.find(id);
	}
}
