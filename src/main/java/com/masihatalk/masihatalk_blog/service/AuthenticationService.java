package com.masihatalk.masihatalk_blog.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masihatalk.masihatalk_blog.dao.AuthenticationDao;
import com.masihatalk.masihatalk_blog.entity.Admin;
import com.masihatalk.masihatalk_blog.entity.blg_user_profile;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationDao profileDao;
	
	
	public Boolean SignUp(blg_user_profile profile) {
		UUIDGenerator rand_id = new UUIDGenerator();
		String userId = rand_id.toString();
		int atIndex = userId.indexOf("@");
		if(atIndex != -1) {
			 userId = userId.substring(atIndex+1);
		}
		profile.setUser_id(userId);
		return profileDao.signUp(profile);
	}


	public List<blg_user_profile> getAccounts() {
		return profileDao.getAccounts();
	}


	public Object LogIn(blg_user_profile user) {
		String email = user.getEmail();
		String password = user.getPassword();
		return profileDao.LogIn(email,password);
	}


	public boolean checkexistemail(String email) {
		return profileDao.checkexistemail(email);
	}


	public blg_user_profile getUserprofile(String user_id) {
		return profileDao.getUserprofile(user_id);
	}


	public boolean updateUserprofile(blg_user_profile profile) {
		System.out.println("Service profile debug => "+ profile);
		return profileDao.updateUserprofile(profile);
	}


	public Admin AdminLogin(Admin admin) {
		
		return profileDao.AdminLogin(admin);
	}


	public boolean deleteUser(String user_id) {
		// TODO Auto-generated method stub
		return profileDao.deleteUser(user_id);
	}

}
