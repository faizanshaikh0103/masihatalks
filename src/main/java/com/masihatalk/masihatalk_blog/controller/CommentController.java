package com.masihatalk.masihatalk_blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masihatalk.masihatalk_blog.entity.blg_comments;
import com.masihatalk.masihatalk_blog.service.CommentService;

@RestController
@CrossOrigin
public class CommentController {

	@Autowired
	private CommentService service;
	
	@GetMapping("/getcommentsby/{bid}")
	public List<blg_comments> getAllCommentsbybid(@PathVariable long bid) {
		List<blg_comments> clist = service.getAllcommentbybid(bid);
		return clist;
	}
	
	@PostMapping("/postcomment")
	public boolean postComment(@RequestBody blg_comments comment) {
		return service.postComment(comment);
	}
	
	@DeleteMapping("/deletecomment/{cid}")
	public boolean deleteComment(@PathVariable long cid){
		return service.deleteComment(cid);
	}
}
