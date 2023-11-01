package com.masihatalk.masihatalk_blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Blg_blog_post_views {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long view_count;
	private long blog_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getView_count() {
		return view_count;
	}
	public void setView_count(long view_count) {
		this.view_count = view_count;
	}
	public long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "Blg_blog_post_views [id=" + id + ", view_count=" + view_count + ", blog_id=" + blog_id + "]";
	}
	
	
}
