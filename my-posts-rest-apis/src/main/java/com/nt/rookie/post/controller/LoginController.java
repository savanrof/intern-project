package com.nt.rookie.post.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.exception.SomethingNotFoundException;
import com.nt.rookie.post.service.impl.AuthorServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1.0/author")
public class LoginController {
	private final static Logger log = LoggerFactory.getLogger(PostController.class);
	
	AuthorServiceImpl authorServiceImpl;
	
	@Autowired
	public LoginController(AuthorServiceImpl authorServiceImpl) {
		super();
		this.authorServiceImpl = authorServiceImpl;
	}
	
	@ApiOperation(value = "Get Author from DB by Username", response = Object.class)
	@RequestMapping(value = "/author/{username}", method = RequestMethod.GET)
	public AuthorDTO findAuthorByUsername(@RequestParam String username) {
		log.info("v1: Calling get author by username from /author/{id} with GET");
		AuthorDTO author = authorServiceImpl.getAuthorByUsername(username);
		if(author==null) {
			log.info("v1: Can not find Author with username "+username);
			throw new SomethingNotFoundException("Can not found author with username: "+ username);
		}
		log.info("v1: Author founded with username: "+username);
		return author;
	}
	
	@ApiOperation(value = "Get Author from DB by Email", response = Object.class)
	@RequestMapping(value = "/author/{email}", method = RequestMethod.GET)
	public AuthorDTO findAuthorByEmail(@RequestParam String email) {
		log.info("v1: Calling get author by email from /author/{email} with GET");
		AuthorDTO author = authorServiceImpl.getAuthorByEmail(email);
		if(author==null) {
			log.info("v1: Can not find Author with email "+email);
			throw new SomethingNotFoundException("Can not found author with email: "+ email);
		}
		log.info("v1: Author founded with email: "+email);
		return author;
	}
	
	@ApiOperation(value = "Login", response = Object.class)
	@RequestMapping(value = "/author/login", method = RequestMethod.POST)
	public AuthorDTO loginAuthor(@RequestParam String username,
									@RequestParam String password) {
		log.info("v1: Calling login user from /author/login with POST");
		AuthorDTO author = authorServiceImpl.loginTest(username, password);
		if(author==null) {
			log.info("v1: Wrong Username or Password with Username "+username);
			throw new SomethingNotFoundException("Login Failed with username: "+ username);
		}
		log.info("v1: Login success with Username: "+username);
		return author;
	}
}
