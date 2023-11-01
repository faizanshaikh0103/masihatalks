package com.masihatalk.masihatalk_blog.additional;

public class Pendingrequest {

	private long id;
	private String user_id;
	private String user_name;
	private String email;
	private String message;
	private String status;
	private String access;
	
	public Pendingrequest() {
		
	}
	public Pendingrequest(long id, String user_id, String user_name, String email, String mesage, String status,
			String access) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.email = email;
		this.message = mesage;
		this.status = status;
		this.access = access;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String mesage) {
		this.message = mesage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	
	
}
