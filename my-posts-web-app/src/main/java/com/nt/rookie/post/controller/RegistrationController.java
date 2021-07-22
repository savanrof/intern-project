package com.nt.rookie.post.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nt.rookie.post.dto.AuthorDTO;
import com.nt.rookie.post.service.impl.AuthorServiceImpl;

@Controller
public class RegistrationController {
	private AuthorServiceImpl authorService;
	
	@Autowired
	public RegistrationController(AuthorServiceImpl authorService) {
		super();
		this.authorService = authorService;
	}
	
	@GetMapping("/registration")
	public ModelAndView prepare(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("registration");
		mav.addObject("registration", new AuthorDTO());
		return mav;
	}
	
	@PostMapping("/registration")
	public String registration(@RequestParam String firstname,
								@RequestParam String lastname,
								@RequestParam String email,
								@RequestParam Date birthdate,
								@RequestParam String username,
								@RequestParam String password,
								@ModelAttribute("registration") AuthorDTO author,
								Model model) throws ParseException {
		if (!authorService.checkAuthorExistByUsername(username) && !authorService.checkAuthorExistsByEmail(email)) {
			
			author.setFirstName(firstname);
			author.setLastName(lastname);
			author.setEmail(email);
			author.setBirthDate(birthdate);
			author.setCreatedDate(new Date());
			author.setUsername(username);
			author.setPassword(password);
			authorService.createAuthor(author);
			return "redirect:login";
		}
		model.addAttribute("message","Registration failed! Try again!");
		return null;
	}
	
	 @InitBinder
	  public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class,
	        new CustomDateEditor(dateFormat, false));
	  }
}
