package com.nt.rookie.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nt.rookie.post.dto.PostDTO;
import com.nt.rookie.post.model.MyOptionsForm;
import com.nt.rookie.post.service.impl.PostServiceImpl;

@Controller
public class HomeController {
	
	PostServiceImpl postService;
	
	@Autowired
	public HomeController(PostServiceImpl searchPost) {
		this.postService = searchPost;
	}

	@GetMapping("home")
	public ModelAndView homePage(HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		List<PostDTO> posts = postService.get10NewestPosts();
		mv.addObject("listPosts", posts);
		return mv;
	}
	
	@GetMapping("/searchpost")
	public ModelAndView searchPost(@RequestParam(value="searchTitle") String keyword, HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		List<PostDTO> posts = postService.findByTitleLike(keyword);
		mv.addObject("listPosts", posts);
		return mv;
	}
	
	
}
