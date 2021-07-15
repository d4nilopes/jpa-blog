package com.devskiller.tasks.blog.service.impl;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

import java.util.List;

public interface ICommentService {
	List<CommentDto> getCommentsForPost(Long postId);

	Long addComment (NewCommentDto newCommentDto);
}
