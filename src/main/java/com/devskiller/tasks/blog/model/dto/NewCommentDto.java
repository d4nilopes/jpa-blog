package com.devskiller.tasks.blog.model.dto;

import lombok.Data;

@Data
public class NewCommentDto {

	private Long postId;

	private String author;

	private String content;


}
