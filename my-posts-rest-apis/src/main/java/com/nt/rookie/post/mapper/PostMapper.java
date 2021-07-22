package com.nt.rookie.post.mapper;

import org.springframework.stereotype.Component;

import com.nt.rookie.post.dto.PostDTO;
import com.nt.rookie.post.entity.Post;
@Component
public class PostMapper {

	public PostDTO fromEntity(Post entity) {
		PostDTO dto = new PostDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setDescription(entity.getDescription());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setAuthor(entity.getAuthor());
		return dto;
	}

	public Post fromDTO(PostDTO payload) {
		return fromDTO(new Post(), payload);
	}

	public Post fromDTO(Post entity, PostDTO payload) {
		entity.setTitle(payload.getTitle());
		entity.setContent(payload.getContent());
		entity.setDescription(payload.getDescription());
		entity.setCreatedDate(payload.getCreatedDate());
		entity.setAuthor(payload.getAuthor());
		return entity;
	}
}
