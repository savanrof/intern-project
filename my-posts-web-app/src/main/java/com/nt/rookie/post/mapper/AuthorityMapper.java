package com.nt.rookie.post.mapper;

import org.springframework.stereotype.Component;

import com.nt.rookie.post.dto.AuthorityDTO;
import com.nt.rookie.post.entity.Authority;

@Component
public class AuthorityMapper {
	
	public AuthorityDTO fromEntity(Authority entity) {
		AuthorityDTO dto = new AuthorityDTO();
		dto.setId(entity.getId());
		dto.setAuthor(entity.getAuthor());
		dto.setAuthority(entity.getAuthority());
		return dto;
	}
	
	public Authority fromDTO(AuthorityDTO payload) {
		return fromDTO(new Authority(), payload);
	}

	private Authority fromDTO(Authority entity, AuthorityDTO payload) {
		entity.setId(payload.getId());
		entity.setAuthor(payload.getAuthor());
		entity.setAuthority(payload.getAuthority());
		return entity;
	}
}
