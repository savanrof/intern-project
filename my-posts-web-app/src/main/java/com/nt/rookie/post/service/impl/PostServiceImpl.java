package com.nt.rookie.post.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.rookie.post.entity.Author;
import com.nt.rookie.post.entity.Post;
import com.nt.rookie.post.exception.NotFoundException;
import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.dto.PostDTO;
import com.nt.rookie.post.mapper.AuthorMapper;
import com.nt.rookie.post.mapper.PostMapper;
import com.nt.rookie.post.repository.AuthorRepository;
import com.nt.rookie.post.repository.PostRepository;
import com.nt.rookie.post.service.PostService;



@Service
public class PostServiceImpl implements PostService{
	@PersistenceContext
    private EntityManager manager;
	
	private final PostRepository postRepository;
	private final AuthorRepository authorRepository;
	private final PostMapper mapper;
	private final AuthorMapper authormapper;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository, PostMapper postMapper, AuthorMapper authormapper) {
		super();
		this.postRepository = postRepository;
		this.authorRepository = authorRepository;
		this.mapper = postMapper;
		this.authormapper = authormapper;
	}

	@Override
	public List<PostDTO> get10NewestPosts() {
		List<Post> posts = postRepository.findTop10ByOrderByCreatedDateDesc();
		return posts.stream().map(mapper::fromEntity).collect(Collectors.toList());
	}

	@Override
	public PostDTO findPostById(Integer id) {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new NotFoundException("No record found with id " + id);
		}
		return mapper.fromEntity(post);
	}

	@Override
	public List<PostDTO> findByTitleLike(String keyword) {
		List<Post> posts = postRepository.findByTitleContains(keyword);
		return posts.stream().map(mapper::fromEntity).collect(Collectors.toList());
	}

	@Override
	public PostDTO updatePost(Integer id, PostDTO payload) {
		if (id == null) {
			throw new IllegalArgumentException("Post id is null");
		}
		Post post = postRepository.findById(id).orElse(null);
		Post postToUpdate = mapper.fromDTO(post,payload);
		Post postUpdated = postRepository.save(postToUpdate);
		return mapper.fromEntity(postUpdated);
	}

	@Override
	public PostDTO createPost(PostDTO payload, Integer authorId) {
		if (payload == null) {
			throw new IllegalArgumentException("Request payload can not be null");
		}

		if (authorId == null) {
			throw new IllegalArgumentException("Author id can not be null");
		}

		Post post = mapper.fromDTO(payload);
		post.setCreatedDate(new Date());
		Author author = authorRepository.findById(authorId).orElse(null);
		post.setAuthor(author);
		Post createdPost = postRepository.save(post);
		return mapper.fromEntity(createdPost);
	}

//	@Override
//	public boolean getPostFromAuthorID(AuthorDTO author) {
//		List<Post> posts = manager.createNamedQuery("checkAuthorByPost",Post.class)
//									.getResultList();
//		posts.stream().filter()
//		return null;
//	}

}
