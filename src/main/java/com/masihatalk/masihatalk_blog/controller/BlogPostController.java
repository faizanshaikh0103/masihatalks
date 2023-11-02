package com.masihatalk.masihatalk_blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.masihatalk.masihatalk_blog.entity.Blg_blog_post;
import com.masihatalk.masihatalk_blog.entity.Blg_blog_post_views;
import com.masihatalk.masihatalk_blog.service.BlogPostService;

@RestController
@CrossOrigin
public class BlogPostController {
	@Autowired
	private BlogPostService service;

	@PostMapping("/postblog")
	public ResponseEntity postblog(
			@RequestParam("user_id") String user_id,
			@RequestParam("blog_title") String blog_title,
			@RequestParam("blog_content") String blog_content,
			@RequestParam("image_url") MultipartFile image_url,
			@RequestParam("author_name") String author_name) {
		try {
			Blg_blog_post blg_post = new Blg_blog_post();
			blg_post.setUser_id(user_id);
			blg_post.setBlog_title(blog_title);
			blg_post.setBlog_content(blog_content);
			blg_post.setImage_url(image_url.getBytes());
			blg_post.setAuthor_name(author_name);
			service.postblog(blg_post);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

	@GetMapping("/getbloglist")
	public List<Blg_blog_post> getbloglist() {
		return service.getbloglist();
	}

	@GetMapping("blogbyid/{id}")
	public Blg_blog_post getblogbyid(@PathVariable long id) {
		return service.getblogbyid(id);
	}

	@GetMapping("/userblogs/{user_id}")
	public List<Blg_blog_post> getBlogsbyUserId(@PathVariable String user_id) {
		System.out.println("user Id => " + user_id);
		return service.getBlogsbyUserId(user_id);
	}

	@PutMapping("/updateblog/{bid}")
	public String updateblog(@PathVariable long bid, @RequestBody Blg_blog_post blg) {
		return service.updateblog(bid, blg);
	}

	@DeleteMapping("deleteblog/{bid}")
	public boolean deleteblog(@PathVariable long bid) {
		return service.deleteblog(bid);
	}

	@GetMapping("/viewcount/{bid}")
	public Blg_blog_post_views blogViewCount(@PathVariable long bid) {
		return service.blogViewCount(bid);
	}

	@PostMapping("/increaseCount")
	public boolean IncreaseViewCount(@RequestBody long bid) {
		Blg_blog_post_views view = new Blg_blog_post_views();
		view.setBlog_id(bid);
		view.setView_count(view.getView_count());
		return service.IncreaseViewCount(view);
	}

	@GetMapping("/modifycolumn")
	public boolean changeDatatype() {
		return service.changedataType();
	}
}
