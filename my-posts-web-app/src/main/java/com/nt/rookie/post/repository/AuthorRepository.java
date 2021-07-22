package com.nt.rookie.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.rookie.post.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	Author findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
