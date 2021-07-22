package com.nt.rookie.post.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="posts")
public class Post {

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "title", length = 50)
    private String title;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "content")
    private String content;
	
	
	@Column(name ="created_date")
    private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
    private Author author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Post(Integer id, String title, String description, String content, Date createdDate, Author author) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.createdDate = createdDate;
		this.author = author;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", createdDate=" + createdDate + ", author=" + author + "]";
	}
	
	
}
