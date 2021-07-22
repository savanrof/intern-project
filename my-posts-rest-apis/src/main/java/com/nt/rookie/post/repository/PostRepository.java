package com.nt.rookie.post.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.rookie.post.entity.Author;
import com.nt.rookie.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	public List<Post> findByTitleContains(String keyword);
	public List<Post> findByTitle(String title);
	public List<Post> findByCreatedDateLessThanEqualAndCreatedDateGreaterThanEqual(Date from, Date to);
	public List<Post> findByAuthorIdAndCreatedDateLessThanEqualAndCreatedDateGreaterThanEqual(Integer authoId, Date from, Date to);
	public List<Post> findTop10ByOrderByCreatedDateDesc();
}
