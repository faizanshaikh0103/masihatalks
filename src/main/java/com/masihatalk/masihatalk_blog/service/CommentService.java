package com.masihatalk.masihatalk_blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masihatalk.masihatalk_blog.dao.CommentDao;
import com.masihatalk.masihatalk_blog.entity.blg_comments;

@Service
public class CommentService {

	@Autowired
	private CommentDao dao;

	public List<blg_comments> getAllcommentbybid(long bid) {
	  return dao.getAllcommentbybid(bid);
	}

	public boolean postComment(blg_comments comment) {
		LocalDateTime comment_date = LocalDateTime.now();
		comment.setComment_date(comment_date);
		return dao.postComment(comment);
	}

	public boolean deleteComment(long cid) {
		return dao.deleteComment(cid);
	}
}
