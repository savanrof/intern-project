package com.nt.rookie.post.dto;

import java.util.Date;

import com.nt.rookie.post.entity.Author;
import com.nt.rookie.post.entity.Post;


public class PostDTO {

    private Integer id;

    private String title;

    private String description;

    private String content;

    private Date createdDate;
    
    private Author author;

    public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

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

//	public PostDTO(Post entity) {
//		this.id = entity.getId();
//		this.title = entity.getTitle();
//		this.description = entity.getDescription();
//		this.content = entity.getContent();
//		this.createdDate = entity.getCreatedDate();
//		this.author = entity.getAuthor();
//	}

	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", createdDate=" + createdDate + ", author=" + author + "]";
	}
    
}
