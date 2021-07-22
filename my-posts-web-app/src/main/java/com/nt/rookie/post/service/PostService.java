package com.nt.rookie.post.service;

import java.util.List;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.dto.PostDTO;

public interface PostService {
	public List<PostDTO> get10NewestPosts();
	
	public PostDTO findPostById(Integer id);
	
	public List<PostDTO> findByTitleLike(String keyword);
	
	public PostDTO updatePost(Integer id, PostDTO payload);
	
	public PostDTO createPost(PostDTO payload, Integer authorId);
	
	//public boolean getPostFromAuthorID(AuthorDTO author);
}
