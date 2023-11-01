package com.masihatalk.masihatalk_blog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masihatalk.masihatalk_blog.additional.Pendingrequest;
import com.masihatalk.masihatalk_blog.entity.blg_permissions;
import com.masihatalk.masihatalk_blog.entity.blg_permissions;
import com.masihatalk.masihatalk_blog.service.PermissionService;

@RestController
@CrossOrigin
public class PermissionController {

	@Autowired
	private PermissionService service;

	@PostMapping("/sendrequest")
	public boolean sendRequest(
			@RequestParam("user_id") String user_id,
			@RequestParam("message") String message,
			@RequestParam("status") String status,
			@RequestParam("access") String access
			) {
		blg_permissions request = new blg_permissions();
		request.setAccess(access);
		request.setMessage(message);
		request.setStatus(status);
		request.setUser_id(user_id);
	
		return service.sendRequest(request);
	}
	
	@GetMapping("/getallpendingrequest")
	public List<Pendingrequest> getAllRequest() {
		return service.getAllRequest();
	}
	
	@PutMapping("/changestatus")
	public boolean updateStatus(@RequestBody blg_permissions request) {
		return service.updateStatus(request);
	}
	
	@GetMapping("/getallaccepted")
	public List<Pendingrequest> getAllacceptrequest() {
		return service.getAllacceptrequest();
	}
	
	@GetMapping("/checkexistrequest/{user_id}")
	public boolean checkExist(@PathVariable String user_id) {
		return service.checkExist(user_id);
	}
	
	@GetMapping("/getrequestbyid/{user_id}")
	public Pendingrequest getRequestbyId(@PathVariable String user_id) {
		return service.getRequestbyId(user_id);
	}
	
	@GetMapping("/getpermissionbyid/{user_id}")
	public blg_permissions getUserpermission(@PathVariable String user_id) {
		return service.getUserpermission(user_id);
	}
	
	public Pendingrequest getAllrejected() {
		return null;
	}
}
