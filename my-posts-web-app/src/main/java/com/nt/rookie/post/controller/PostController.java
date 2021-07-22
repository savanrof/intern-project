package com.nt.rookie.post.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.dto.PostDTO;
import com.nt.rookie.post.mapper.AuthorMapper;
import com.nt.rookie.post.service.impl.AuthorServiceImpl;
import com.nt.rookie.post.service.impl.PostServiceImpl;

@Controller
public class PostController {
	private PostServiceImpl postService;
	private AuthorServiceImpl authorService;
	private AuthorMapper authorMapper;
	
	@Autowired
	public PostController(PostServiceImpl searchPost, AuthorServiceImpl authorService,  AuthorMapper authorMapper) {
		this.postService = searchPost;
		this.authorService = authorService;
		this.authorMapper = authorMapper;
	}
	
	@GetMapping("/postdetails")
	public ModelAndView postDetails(@RequestParam String id, HttpSession session) {
		ModelAndView mv = new ModelAndView("postdetails");
		PostDTO post = postService.findPostById(Integer.parseInt(id));
		mv.addObject("post", post);
		return mv;
	}
	
	@PutMapping("/postedit")
	public ModelAndView postEdit(@RequestParam String id, HttpSession session) {
		ModelAndView mv = new ModelAndView("postedit");
		PostDTO post = postService.findPostById(Integer.parseInt(id));
//		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//		AuthorDTO author = authorService.getAuthorByUsername(currentUserName);
//		if (post.getAuthor() != authorMapper.fromDTO(author)) {
//			mv.setViewName("alert");
//			mv.addObject("postName",post.getTitle());
//			return mv;
//		}
		//mv.setViewName("postedit");
		mv.addObject("post",post);
		return mv;
	}
	
	@GetMapping("/createpost")
	public ModelAndView postCreatePrepare(HttpSession session) {
		ModelAndView mv = new ModelAndView("createpost");
		return mv;
	}
	
	@PostMapping("/createpost")
	public ModelAndView postCreate(@RequestParam String title,
											@RequestParam String description,
											@RequestParam String content,
											HttpSession session) {
		ModelAndView mv = new ModelAndView("postdetails");
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		AuthorDTO author = authorService.getAuthorByUsername(currentUserName);
		PostDTO post = new PostDTO();
		post.setAuthor(authorMapper.fromDTO(author));
		post.setContent(content);
		post.setDescription(description);
		post.setCreatedDate(new Date());
		PostDTO postCreated = postService.createPost(post, author.getId());
		mv.addObject("post",postCreated);
		return mv;
	}
	
	@PostMapping("/postupdate")
	public ModelAndView postUpdate(@RequestParam String id,
							@RequestParam String newTitle,
							@RequestParam String newDescription,
							@RequestParam String newContent,
							HttpSession session) {
		ModelAndView mv = new ModelAndView("postdetails");
		PostDTO dto = postService.findPostById(Integer.parseInt(id));
		dto.setTitle(newTitle);
		dto.setDescription(newDescription);
		dto.setContent(newContent);
		postService.updatePost(Integer.parseInt(id), dto);
		mv.addObject("post", dto);
		return mv;
	}
}
