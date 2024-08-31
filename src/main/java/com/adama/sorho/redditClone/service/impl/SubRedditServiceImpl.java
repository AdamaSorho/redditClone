package com.adama.sorho.redditClone.service.impl;

import com.adama.sorho.redditClone.adapter.SubRedditAdapter;
import com.adama.sorho.redditClone.dto.SubRedditDto;
import com.adama.sorho.redditClone.model.SubReddit;
import com.adama.sorho.redditClone.repository.SubRedditRepository;
import com.adama.sorho.redditClone.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubRedditServiceImpl implements SubRedditService {
	private final SubRedditRepository subRedditRepository;

	@Override
	public SubRedditDto create(SubRedditDto subRedditDto) {
		SubReddit subReddit = subRedditRepository.save(SubReddit.builder()
				.name(subRedditDto.getName())
				.description(subRedditDto.getDescription())
				.build());

		subRedditDto.setId(subReddit.getId());

		return subRedditDto;
	}

	@Override
	public Page<SubRedditDto> findAll(Pageable pageable) {
		return subRedditRepository.findAll(pageable)
				.map(SubRedditAdapter::getSubRedditDtoFromSubReddit);
	}
}
