package com.masihatalk.masihatalk_blog.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class blg_comments {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long comment_id;
	private String user_id;
	private long blog_id;
	private String comment_content;
	private LocalDateTime comment_date;
	
	
	
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public LocalDateTime getComment_date() {
		return comment_date;
	}
	public void setComment_date(LocalDateTime comment_date) {
		this.comment_date = comment_date;
	}
	@Override
	public String toString() {
		return "blg_comments [comment_id=" + comment_id + ", user_id=" + user_id + ", blog_id=" + blog_id
				+ ", comment_content=" + comment_content + ", comment_date=" + comment_date + "]";
	}
	
}
