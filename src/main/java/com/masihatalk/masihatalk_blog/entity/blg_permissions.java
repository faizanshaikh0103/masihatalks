package com.masihatalk.masihatalk_blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class blg_permissions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String user_id;
	private String status;
	private String message;
	private String access;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	@Override
	public String toString() {
		return "blg_permissions [id=" + id + ", user_id=" + user_id + ", status=" + status + ", message=" + message
				+ ", access=" + access + "]";
	}
}
