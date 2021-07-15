package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;
import com.devskiller.tasks.blog.service.impl.ICommentService;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService implements ICommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	public CommentService(CommentRepository commentRepository,PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {

		return commentRepository.findByPostId(postId)
			.stream()
			.map(comment -> new CommentDto(comment.getPost().getId(), comment.getContent(), comment.getAuthor()
				, comment.getCreationDate()))
			.collect(Collectors.toList());

	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) {

		this.postRepository.findById(newCommentDto.getPostId()).orElseThrow(IllegalArgumentException::new);

		Comment comment = new Comment();

		comment.setPost(new Post(newCommentDto.getPostId(),null,null,null,null));
		comment.setAuthor(newCommentDto.getAuthor());
		comment.setContent(newCommentDto.getContent());
		comment.setCreationDate(LocalDateTime.now());

		this.commentRepository.save(comment);

		return comment.getId();

	}
}
