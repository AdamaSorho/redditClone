package com.adama.sorho.redditClone.repository;

import com.adama.sorho.redditClone.mapper.PostMapper;
import com.adama.sorho.redditClone.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findBySubRedditId(Long subRedditId, Pageable pageable);
	Page<Post> findByUserUsername(String username, Pageable pageable);
}
