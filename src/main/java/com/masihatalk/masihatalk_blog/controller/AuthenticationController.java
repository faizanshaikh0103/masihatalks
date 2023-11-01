package com.masihatalk.masihatalk_blog.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.tool.hbm2ddl.ForeignKeyMetadata;
//import org.junit.platform.engine.reporting.ReportEntry;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.masihatalk.masihatalk_blog.additional.ApiResponse;
import com.masihatalk.masihatalk_blog.entity.Admin;
import com.masihatalk.masihatalk_blog.entity.blg_user_profile;
import com.masihatalk.masihatalk_blog.service.AuthenticationService;

@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationService profileService;
	@Autowired
	private blg_user_profile profile;

	@PostMapping("/signup")
	public Boolean SignUp(
			@RequestParam("user_name") String user_name,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		profile.setUser_name(user_name);
		profile.setEmail(email);
		profile.setPassword(password);
		return profileService.SignUp(profile);
	}

	@GetMapping("/getAccounts")
	public List<blg_user_profile> getAccounts() {
		return profileService.getAccounts();
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> LogIn(@RequestBody blg_user_profile user) {
		Object userprofile = profileService.LogIn(user);
		if (userprofile == null) {
			System.out.println(userprofile);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(401, "Unauthorize", null));
		} else {
			return ResponseEntity.ok(new ApiResponse(200, "Login Success", userprofile));
		}
	}

	@GetMapping("/getuser/{user_id}")
	public blg_user_profile getUserprofile(@PathVariable String user_id) {
		return profileService.getUserprofile(user_id);
	}

	@GetMapping("/checkexist/{email}")
	public boolean checkexistemail(@PathVariable String email) {
		return profileService.checkexistemail(email);
	}

	@PutMapping("/updateuser")
	public ResponseEntity updateUserprofile(
			@RequestParam("uid") String uid,
			@RequestParam("user_id") String user_id,
			@RequestParam("user_name") String user_name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("profile_image") MultipartFile profile_image) {
		int id = Integer.parseInt(uid);
		blg_user_profile userprofile = new blg_user_profile();
		userprofile.setId(id);
		userprofile.setUser_id(user_id);
		userprofile.setEmail(email);
		userprofile.setPassword(password);
		try {
			userprofile.setProfile_image(profile_image.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
		userprofile.setUser_name(user_name);
		boolean result = profileService.updateUserprofile(userprofile);
		if (result) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

	@RequestMapping("/adminlogin")
	public ResponseEntity<ApiResponse> AdminLogin(@RequestBody Admin admin) {
		Admin ad = profileService.AdminLogin(admin);
		if (ad != null) {
			return ResponseEntity.ok().body(new ApiResponse(200, "success", ad));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(401, "Failed", null));
		}
	}

	@DeleteMapping("/deleteuser/{user_id}")
	public boolean deleteUser(@PathVariable String user_id) {
		return profileService.deleteUser(user_id);
	}

}
