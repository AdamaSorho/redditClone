package com.adama.sorho.redditClone.service.impl;

import com.adama.sorho.redditClone.dto.PostDto;
import com.adama.sorho.redditClone.exception.SpringRedditException;
import com.adama.sorho.redditClone.mapper.PostMapper;
import com.adama.sorho.redditClone.model.Post;
import com.adama.sorho.redditClone.model.SubReddit;
import com.adama.sorho.redditClone.model.User;
import com.adama.sorho.redditClone.repository.PostRepository;
import com.adama.sorho.redditClone.repository.SubRedditRepository;
import com.adama.sorho.redditClone.service.AuthService;
import com.adama.sorho.redditClone.service.PostService;
import com.adama.sorho.redditClone.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final AuthService authService;
	private final SubRedditRepository subRedditRepository;

	@Override
	public PostDto find(Long id) {
		Post post = this.postRepository.findById(id).orElseThrow(
				() -> new SpringRedditException(AppUtils.notFindException("Post", id.toString())));

		return this.postMapper.mapToDto(post);
	}

	@Override
	public Page<PostDto> findAll(Pageable pageable) {
		return this.postRepository.findAll(pageable)
				.map(this.postMapper::mapToDto);
	}

	@Override
	public Page<PostDto> findBySubReddit(Long subRedditId, Pageable pageable) {
		return this.postRepository.findBySubRedditId(subRedditId, pageable)
				.map(this.postMapper::mapToDto);
	}

	@Override
	public Page<PostDto> findByUser(String username, Pageable pageable) {
		return this.postRepository.findByUserUsername(username, pageable)
				.map(this.postMapper::mapToDto);
	}

	@Override
	public PostDto create(PostDto postDto) {
		User currentUser = this.authService.getCurrentUser();
		SubReddit subReddit = this.subRedditRepository.findByName(postDto.getSubRedditName())
				.orElseThrow(() -> new SpringRedditException(AppUtils.notFindException("SubReddit", postDto.getSubRedditName())));
		Post post = this.postMapper.map(postDto, subReddit, currentUser);
		post = this.postRepository.save(post);

		return this.postMapper.mapToDto(post);
	}
}
