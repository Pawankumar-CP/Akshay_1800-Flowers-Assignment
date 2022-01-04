package com.cpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@JsonProperty("userId")
	private Integer userId;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title")
	private String title;
	

	@JsonProperty("body")
	
	private String body;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	
	
	

}

