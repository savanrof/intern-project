package com.nt.rookie.post.service;

import com.nt.rookie.post.dto.AuthorDTO;

public interface AuthorService {
	public AuthorDTO getAuthorByUsername(String username);
	
	boolean checkAuthorExistByUsername(String username);
	
	public AuthorDTO getAuthorByEmail(String email);
	
	public AuthorDTO loginTest(String username, String password);
}
