package com.adama.sorho.redditClone.service;

import com.adama.sorho.redditClone.dto.SubRedditDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubRedditService {
	SubRedditDto create(SubRedditDto subRedditDto);
	Page<SubRedditDto> findAll(Pageable pageable);
}
