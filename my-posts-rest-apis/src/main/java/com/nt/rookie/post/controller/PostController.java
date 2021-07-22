package com.nt.rookie.post.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.dto.PostDTO;
import com.nt.rookie.post.exception.SomethingNotFoundException;
import com.nt.rookie.post.service.impl.AuthorServiceImpl;
import com.nt.rookie.post.service.impl.PostServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1.0/post")
public class PostController {
	private final static Logger log = LoggerFactory.getLogger(PostController.class);
	
	private PostServiceImpl postService;
	
	@Autowired
	public PostController(PostServiceImpl searchPost) {
		this.postService = searchPost;
	}
	
	@ApiOperation(value = "Get 10 newest Posts from DB", response = Object.class)
	@RequestMapping(value = "/newestpost", method = RequestMethod.GET)
	public List<PostDTO> homePage() {
		log.info("v1: Calling get 10 newest posts from /postdetails");
		List<PostDTO> posts = postService.get10NewestPosts();
		return posts;
	}
	
	@ApiOperation(value = "Search Post by title like from DB", response = Object.class)
	@RequestMapping(value = "/searchpost", method = RequestMethod.GET)
	public List<PostDTO> searchPost(@RequestParam(value="searchTitle") String keyword) {
		log.info("v1: Calling search posts by title like /postdetails");
		List<PostDTO> posts = postService.findByTitleLike(keyword);
		return posts;
	}
	
	@ApiOperation(value = "Get Post from DB by Post Id", response = Object.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PostDTO postDetails(@PathVariable int id) {
		log.info("v1: Calling get post by id from /post/{id} with GET");
		PostDTO post = postService.findPostById(id);
		if (post!= null) {
			log.info("v1: Post details with id: " + id);
			//System.err.println(post.getAuthor().getUsername());
			return post;
		} else {
			log.info("v1: Post not found");
			throw new SomethingNotFoundException("Can not found post with id: "+ id);
		}
		
	}
	
	@ApiOperation(value = "Create post", response = Object.class)
	@RequestMapping(value = "/createpost", method = RequestMethod.POST)
	public ResponseEntity<PostDTO> postCreate(@RequestBody PostDTO post,
											@RequestParam int authorId) {
		log.info("v1: Calling create post from /createpost with POST");
		PostDTO postCreated = postService.createPost(post, authorId);
		log.info("v1: Post created with id :" + postCreated.getId());
		return new ResponseEntity<PostDTO>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Edit post", response = Object.class)
	@RequestMapping(value = "/postupdate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PostDTO> postUpdate(@RequestBody PostDTO post) {
		log.info("v1: Calling edit post from /postupdate with PUT");
		PostDTO dto = postService.findPostById(post.getId());
		if (dto == null) {
			log.info("v1: Post not found with id: " +post.getId());
			throw new SomethingNotFoundException("Can not found post with id: "+ post.getId());
		}
		postService.updatePost(post.getId(), post);
		log.info("v1: Post updated with id: " +post.getId());
		return new ResponseEntity<PostDTO>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete post", response = Object.class)
	@RequestMapping(value = "/postdelete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PostDTO> postDelete(@RequestParam int id) {
		log.info("v1: Calling delete post from /postdelete with DELETE");
		if(!postService.deletePostById(id)) {
			log.info("v1: Cannot delete Post with post id: " +id);
			throw new SomethingNotFoundException("Can not found post with id: " +id);
		}
		log.info("v1: Deleted post with post id : " +id);
		return new ResponseEntity<PostDTO>(HttpStatus.OK);
	}
}
