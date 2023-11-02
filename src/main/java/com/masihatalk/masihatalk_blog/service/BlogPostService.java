package com.masihatalk.masihatalk_blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masihatalk.masihatalk_blog.dao.BlogPostDao;
import com.masihatalk.masihatalk_blog.entity.Blg_blog_post;
import com.masihatalk.masihatalk_blog.entity.Blg_blog_post_views;

@Service
public class BlogPostService {

	@Autowired
	BlogPostDao dao;

	public String postblog(Blg_blog_post blg) {
		LocalDateTime date = LocalDateTime.now();
		blg.setPost_date(date);
		return dao.postblog(blg);
	}

	public List<Blg_blog_post> getbloglist() {

		return dao.getbloglist();
	}

	public Blg_blog_post getblogbyid(long id) {
		return dao.getblogbyid(id);
	}

	public String updateblog(long bid, Blg_blog_post blg) {
		return dao.updateblog(bid, blg);
	}

	public boolean deleteblog(long bid) {
		return dao.deleteblog(bid);
	}

	public List<Blg_blog_post> getBlogsbyUserId(String user_id) {
		return dao.getBlogsbyUserId(user_id);
	}

	public Blg_blog_post_views blogViewCount(long bid) {
		return dao.blogViewCount(bid);
	}

	public boolean IncreaseViewCount(Blg_blog_post_views view) {
		return dao.IncreaseViewCount(view);
	}

	public boolean changedataType() {
		return dao.changedataType();
	}

}
