package com.nt.rookie.post.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "authors")
public class Author {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "username", unique = true, nullable = false)
    private String username;
    
	@Column(name ="password")
    private String password;
	
	@Column(name ="first_name")
    private String firstName;
	
	@Column(name ="last_name")
    private String lastName;
	
	
	@Column(name ="email", unique = true, nullable = false)
    private String email;
	
	
	@Column(name ="birth_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
	
	@Column(name ="create_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	//@JsonManagedReference
    private List<Post> posts;
	
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	//@JsonManagedReference
	private Set<Authority> authorities;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public Set<Authority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}


	public Author(Integer id, String username, String password, String firstName, String lastName, String email,
			Date birthDate, Date createdDate, List<Post> posts, Set<Authority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.createdDate = createdDate;
		this.posts = posts;
		this.authorities = authorities;
	}


	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Author [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", birthDate=" + birthDate + ", createdDate="
				+ createdDate + ", posts=" + posts.size() + ", authority=" + authorities + "]";
	}
	

}
