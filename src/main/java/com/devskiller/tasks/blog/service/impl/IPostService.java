package com.devskiller.tasks.blog.service.impl;

import com.devskiller.tasks.blog.model.dto.PostDto;

public interface IPostService {
	PostDto getPost(Long id);
}
