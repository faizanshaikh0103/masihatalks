package com.masihatalk.masihatalk_blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masihatalk.masihatalk_blog.additional.Pendingrequest;
import com.masihatalk.masihatalk_blog.dao.PermissionDao;
import com.masihatalk.masihatalk_blog.entity.blg_permissions;
import com.masihatalk.masihatalk_blog.entity.blg_permissions;

@Service
public class PermissionService {

	@Autowired
	private PermissionDao dao;
	
	public boolean sendRequest(blg_permissions request) {
		
		return dao.sendRequest(request);
	}

	public List<Pendingrequest> getAllRequest() {
		
		return dao.getAllRequest();
	}

	public boolean updateStatus(blg_permissions request) {
		return dao.updateStatus(request);
	}

	public List<Pendingrequest> getAllacceptrequest() {
		return dao.getAllacceptrequest();
	}

	public boolean checkExist(String user_id) {
		return dao.checkExist(user_id);
	}

	public Pendingrequest getRequestbyId(String user_id) {
		return dao.getRequestbyId(user_id);
	}

	public blg_permissions getUserpermission(String user_id) {
		return dao.getUserpermission(user_id);
	}
}
