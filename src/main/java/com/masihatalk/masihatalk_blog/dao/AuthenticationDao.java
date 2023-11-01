
package com.masihatalk.masihatalk_blog.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.masihatalk.masihatalk_blog.entity.Admin;
import com.masihatalk.masihatalk_blog.entity.Blg_blog_post;
import com.masihatalk.masihatalk_blog.entity.Blg_blog_post_views;
import com.masihatalk.masihatalk_blog.entity.blg_comments;
import com.masihatalk.masihatalk_blog.entity.blg_user_profile;

@Repository
public class AuthenticationDao {

	@Autowired
	private SessionFactory sf;
	@Autowired
	private blg_user_profile user;
	
	private String userName = "Sahir Masiha";
	private String userId = "MAsBlG8w";
	private String email = "sahirm@gmail.com";
	
	public Boolean signUp(blg_user_profile profile) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.save(profile);
		tr.commit();
		session.close();
		return true;
		}
	


	public List<blg_user_profile> getAccounts() {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(blg_user_profile.class);
		List<blg_user_profile> list = cr.list();
		session.close();
		return list;
	}



	public Object LogIn(String email, String password) {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(blg_user_profile.class);
		cr.add(Restrictions.and(Restrictions.eq("email", email),Restrictions.eq("password", password)));
		List<blg_user_profile> confirm =  cr.list();
		session.close();
		System.out.println("confirm := "+confirm);
		if(confirm.isEmpty() || confirm == null) {
			return null;
		} else {

			blg_user_profile userprofile = confirm.get(0);
			return userprofile;
		}
	}



	public boolean checkexistemail(String email) {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(blg_user_profile.class);
		cr.add(Restrictions.eq("email", email));
		if(!(cr.list().isEmpty() || cr.list() == null)) {
			session.close();
			return true;
		} else {
			session.close();
		return false;
		}
	}



	public blg_user_profile getUserprofile(String user_id) {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(blg_user_profile.class);
		cr.add(Restrictions.eq("user_id", user_id));
		blg_user_profile user = (blg_user_profile) cr.uniqueResult();
		session.close();
		return user;
	}



	public boolean updateUserprofile(blg_user_profile profile) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Criteria cr = session.createCriteria(blg_user_profile.class);
			cr.add(Restrictions.eq("id", profile.getId()));
			blg_user_profile userp = (blg_user_profile) cr.uniqueResult();
			if(userp != null)
			{
				userp.setId(profile.getId());
				userp.setEmail(profile.getEmail());
				userp.setPassword(profile.getPassword());
				userp.setUser_name(profile.getUser_name());
				userp.setProfile_image(profile.getProfile_image());
				session.update(userp);
				tr.commit();
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) 
		{
			if(tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return false;
		}
		finally 
		{
			session.close();
		}
		
		
	}



	public Admin AdminLogin(Admin admin) {
		Session session = sf.openSession();
		
		try {
			Criteria cr = session.createCriteria(Admin.class);
			cr.add(Restrictions.and(Restrictions.eq("username", admin.getUsername()),Restrictions.eq("password", admin.getPassword())));
			Admin ad = (Admin) cr.uniqueResult();
			if(ad != null) {
				return ad;
			} else {
				return null;
			}
		} catch(Exception e) {
			return null;
		} finally {
			session.close();
		}	
		}



	public boolean deleteUser(String user_id) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		try {
			blg_user_profile user = new blg_user_profile();
			Criteria cr = session.createCriteria(blg_user_profile.class);
			cr.add(Restrictions.eq("user_id", user_id));
			user = (blg_user_profile) cr.uniqueResult();
			if(user != null) {
				Criteria crb = session.createCriteria(Blg_blog_post.class);
				crb.add(Restrictions.eq("user_id", user.getUser_id()));
				List<Blg_blog_post> bloglist = crb.list();
				Criteria crv = session.createCriteria(Blg_blog_post_views.class);
				for (Blg_blog_post blg : bloglist) {
					crv.add(Restrictions.eq("blog_id", blg.getBlog_id()));
					Blg_blog_post_views bbv = (Blg_blog_post_views) crv.uniqueResult();
					session.delete(bbv);
					session.delete(blg);
				}
				Criteria ccr = session.createCriteria(blg_comments.class);
				ccr.add(Restrictions.eq("user_id", user.getUser_id()));
				List<blg_comments> commentlist = ccr.list();
				for (blg_comments comment : commentlist) {
					session.delete(comment);
				}
				
			}
			session.delete(user);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			tr.commit();
			session.close();
		}
		
	}
	}
	


