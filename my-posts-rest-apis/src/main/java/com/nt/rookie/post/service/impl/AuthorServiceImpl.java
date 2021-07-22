package com.nt.rookie.post.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.entity.Author;
import com.nt.rookie.post.mapper.AuthorMapper;
import com.nt.rookie.post.repository.AuthorRepository;
import com.nt.rookie.post.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorRepository authorRepository;
	private final AuthorMapper mapper;
	
	@Autowired
	public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper mapper) {
		super();
		this.authorRepository = authorRepository;
		this.mapper = mapper;
	}

	@Override
	public AuthorDTO getAuthorByUsername(String username) {
		Author author = authorRepository.findByUsername(username);
		return mapper.fromEntity(author);
	}

	@Override
	public boolean checkAuthorExistByUsername(String username) {
		return authorRepository.existsByUsername(username);
	}

	@Override
	public AuthorDTO getAuthorByEmail(String email) {
		Author author = authorRepository.findByEmail(email);
		return mapper.fromEntity(author);
	}

	@Override
	public AuthorDTO loginTest(String username, String password) {
		Author author = authorRepository.findByUsernameAndPassword(username, password);
		return mapper.fromEntity(author);
	}
	
	
}
