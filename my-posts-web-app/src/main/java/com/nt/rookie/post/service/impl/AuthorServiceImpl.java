package com.nt.rookie.post.service.impl;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public boolean createAuthor(AuthorDTO payload) {
		if (payload == null) {
			throw new IllegalArgumentException("Request payload can not be null");
		}
		try {
		Author author = mapper.fromDTO(payload);
		author.setCreatedDate(new Date());
		authorRepository.save(author);
		return true;
		} catch (Exception ex) {
			System.err.println(ex);
			return false;
		}
	}

	

	@Override
	public boolean checkAuthorExistsByEmail(String email) {
		return authorRepository.existsByEmail(email);
	}

	
}
