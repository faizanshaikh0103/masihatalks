package com.masihatalk.masihatalk_blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.stereotype.Component;

@Entity
@Component
public class blg_user_profile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String user_id;
	private String user_name;
	private String email;
	private String password;
	@Lob
	private byte[] profile_image;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(byte[] profile_image) {
		this.profile_image = profile_image;
	}
	@Override
	public String toString() {
		return "blg_user_profile [id=" + id + ", user_id=" + user_id + ", user_name=" + user_name + ", email=" + email
				+ ", password=" + password + ", profile_image=" + profile_image + "]";
	}
	
}
