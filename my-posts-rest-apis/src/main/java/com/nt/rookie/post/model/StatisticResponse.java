package com.nt.rookie.post.model;

import java.io.Serializable;

public class StatisticResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String author;
	private Integer numberOfPosts;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getNumberOfPosts() {
		return numberOfPosts;
	}
	public void setNumberOfPosts(Integer numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
	}
	
}
