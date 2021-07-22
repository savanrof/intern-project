package com.nt.rookie.post.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.entity.Authority;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthorServiceImpl authorServiceImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBuilder userBuilder = null;
		if(authorServiceImpl.checkAuthorExistByUsername(username)) {
			AuthorDTO authorDTO = authorServiceImpl.getAuthorByUsername(username);
			userBuilder = User.withUsername(username);
			userBuilder.password(new BCryptPasswordEncoder().encode(authorDTO.getPassword()));
			userBuilder.roles(authorDTO.getAuthorities().stream()
					.map(Authority::getAuthority)
					.collect(Collectors.toList())
					.toArray(String[]::new)
					);
		} else {
			throw new UsernameNotFoundException("User not found");
		}
		return userBuilder.build();
	}

}
