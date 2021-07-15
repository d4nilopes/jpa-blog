package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.impl.ICommentService;
import com.devskiller.tasks.blog.service.impl.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final IPostService postService;

	private final ICommentService commentsService;

	public PostController(IPostService postService, ICommentService commentsService)
	{
		this.postService = postService;
		this.commentsService = commentsService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getCommentsForPost(@PathVariable Long id) {

		return commentsService.getCommentsForPost(id);
	}

	@PostMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public Long postCommentsForPost(@RequestBody NewCommentDto request) {

		return commentsService.addComment(request);
	}

}
