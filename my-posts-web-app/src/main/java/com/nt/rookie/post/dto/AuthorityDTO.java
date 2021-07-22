package com.nt.rookie.post.dto;

import com.nt.rookie.post.entity.Author;

public class AuthorityDTO {
	
	private Integer id;
	    
	private Author author;
	
    private String authority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public AuthorityDTO(Integer id, Author author, String authority) {
		super();
		this.id = id;
		this.author = author;
		this.authority = authority;
	}

	public AuthorityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AuthorityDTO [id=" + id + ", author=" + author + ", authority=" + authority + "]";
	}
    
    
}
