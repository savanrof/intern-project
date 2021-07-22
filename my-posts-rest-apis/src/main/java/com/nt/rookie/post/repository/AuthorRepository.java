package com.nt.rookie.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.rookie.post.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	public Author findByUsername(String username);
	public boolean existsByUsername(String username);
	public Author findByEmail(String email);
	public Author findByUsernameAndPassword(String username,String password);

}
