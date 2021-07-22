package com.nt.rookie.post.service;

import org.springframework.data.jpa.repository.Query;

import com.nt.rookie.post.dto.AuthorDTO;

public interface AuthorService {
	public AuthorDTO getAuthorByUsername(String username);
	
	public boolean checkAuthorExistByUsername(String username);
	
	public boolean createAuthor(AuthorDTO payload);
	
	public boolean checkAuthorExistsByEmail(String email);
	
}
