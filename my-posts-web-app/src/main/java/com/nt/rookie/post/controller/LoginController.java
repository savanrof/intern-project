package com.nt.rookie.post.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.model.LoginDto;
import com.nt.rookie.post.service.impl.AuthorServiceImpl;

@Controller
public class LoginController {
	
	AuthorServiceImpl authorServiceImpl;
	
	@Autowired
	public LoginController(AuthorServiceImpl authorServiceImpl) {
		super();
		this.authorServiceImpl = authorServiceImpl;
	}

	@GetMapping("/login")
	public ModelAndView prepare(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new AuthorDTO());
		return mav;
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username,
								@RequestParam String password,
								@ModelAttribute("login") AuthorDTO dto,
								Model model,
								HttpSession session) {
		if (authorServiceImpl.checkAuthorExistByUsername(username)) {
			dto = authorServiceImpl.getAuthorByUsername(username);
			if (username.equals(dto.getUsername()) && password.equals(dto.getPassword())) {
				session.setAttribute("username", dto.getUsername());
				return "redirect:home";
			}
		}
		model.addAttribute("message","Login failed");
		return null;
	}
	

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if (session != null) {
			session.removeAttribute("username");
			session.invalidate();
		}
		return "redirect:login";
	}
}
