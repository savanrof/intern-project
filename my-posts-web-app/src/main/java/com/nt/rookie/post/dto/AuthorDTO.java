package com.nt.rookie.post.dto;

import java.util.Date;
import java.util.Set;

import com.nt.rookie.post.entity.Authority;

public class AuthorDTO {
	private int id;
	private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private Date createdDate;
    private Set<Authority> authorities;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthdate) {
		this.birthDate = birthdate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date added) {
		this.createdDate = added;
	}
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	public AuthorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorDTO(int id,String username, String password, String firstName, String lastName, String email, Date birthDate,
			Date createdDate, Set<Authority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.createdDate = createdDate;
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "AuthorDTO ["+"id="+id+"username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", birthDate=" + birthDate + ", createdDate=" + createdDate + ", authorities="
				+ authorities + "]";
	}
    
    
}
