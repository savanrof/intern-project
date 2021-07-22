package com.nt.rookie.post.mapper;

import org.springframework.stereotype.Component;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.entity.Author;

@Component
public class AuthorMapper {
	
	public AuthorDTO fromEntity(Author entity) {
		AuthorDTO dto = new AuthorDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setPassword(entity.getPassword());
		dto.setEmail(entity.getPassword());
		dto.setBirthDate(entity.getBirthDate());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setAuthorities(entity.getAuthorities());
		return dto;
	}
	
	public Author fromDTO(AuthorDTO payload) {
		return fromDTO(new Author(), payload);
	}
	
	public Author fromDTO(Author entity, AuthorDTO payload) {
		entity.setId(payload.getId());
		entity.setUsername(payload.getUsername());
		entity.setFirstName(payload.getFirstName());
		entity.setLastName(payload.getLastName());
		entity.setPassword(payload.getPassword());
		entity.setEmail(payload.getPassword());
		entity.setBirthDate(payload.getBirthDate());
		entity.setCreatedDate(payload.getCreatedDate());
		entity.setAuthorities(payload.getAuthorities());
		return entity;
	}
}
