package com.adama.sorho.redditClone.service.impl;

import com.adama.sorho.redditClone.dto.CommentDto;
import com.adama.sorho.redditClone.exception.SpringRedditException;
import com.adama.sorho.redditClone.integration.EmailSender;
import com.adama.sorho.redditClone.mapper.CommentMapper;
import com.adama.sorho.redditClone.model.Comment;
import com.adama.sorho.redditClone.model.NotificationEmail;
import com.adama.sorho.redditClone.model.Post;
import com.adama.sorho.redditClone.model.User;
import com.adama.sorho.redditClone.repository.CommentRepository;
import com.adama.sorho.redditClone.repository.PostRepository;
import com.adama.sorho.redditClone.service.AuthService;
import com.adama.sorho.redditClone.service.CommentService;
import com.adama.sorho.redditClone.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final AuthService authService;
	private final PostRepository postRepository;
	private final CommentMapper commentMapper;
	private final EmailSender emailSender;

	@Override
	public CommentDto create(CommentDto commentDto) {
		User currentUser = this.authService.getCurrentUser();
		Post post = this.postRepository.findById(commentDto.getPostId())
				.orElseThrow(() -> new SpringRedditException(AppUtils.notFindException("Post", commentDto.getPostId().toString())));
		Comment comment = this.commentMapper.map(commentDto, post, currentUser);
		comment = this.commentRepository.save(comment);

		sendCommentNotificationEmail(post, currentUser);

		return this.commentMapper.mapToDto(comment);
	}

	private void sendCommentNotificationEmail(Post post, User currentUser) {
		this.emailSender.send(NotificationEmail.builder()
				.subject(AppUtils.newCommentSubject(currentUser.getUsername()))
				.recipient(post.getUser().getEmail())
				.body(AppUtils.newCommentBody(post.getUser().getUsername(), post.getUrl()))
				.build());
	}

	@Override
	public Page<CommentDto> findByPost(Long postId, Pageable pageable) {
		return this.commentRepository.findByPostId(postId, pageable)
				.map(this.commentMapper::mapToDto);
	}

	@Override
	public Page<CommentDto> findByUser(String username, Pageable pageable) {
		return this.commentRepository.findByUserUsername(username, pageable)
				.map(this.commentMapper::mapToDto);
	}
}
