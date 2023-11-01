package com.masihatalk.masihatalk_blog.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Blg_blog_post {
	
	
	private long blog_id;
	
	private String user_id;
	
	private String blog_title;
	@Lob
	private String blog_content;
	@Lob
	private byte[] image_url;
	private String author_name;
	private LocalDateTime post_date;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}
	public byte[] getImage_url() {
		return image_url;
	}
	public void setImage_url(byte[] image_url) {
		this.image_url = image_url;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public LocalDateTime getPost_date() {
		return post_date;
	}
	public void setPost_date(LocalDateTime post_date) {
		this.post_date = post_date;
	}
	@Override
	public String toString() {
		return "Blg_blog_post [blog_id=" + blog_id + ", user_id=" + user_id + ", blog_title=" + blog_title
				+ ", blog_content=" + blog_content + ", image_url=" + image_url + ", author_name=" + author_name
				+ ", post_date=" + post_date + "]";
	}
	
}
