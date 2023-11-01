package com.masihatalk.masihatalk_blog.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masihatalk.masihatalk_blog.entity.blg_comments;

@Repository
public class CommentDao {

	@Autowired
	private blg_comments comment;
	@Autowired
	private SessionFactory sf;

	public List<blg_comments> getAllcommentbybid(long bid) {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(blg_comments.class);
		cr.add(Restrictions.eq("blog_id", bid));
		cr.addOrder(Order.desc("comment_id"));
		List<blg_comments> list = cr.list();
		session.close();
		return list;
	}

	public boolean postComment(blg_comments comment2) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.save(comment2);
		tr.commit();
		session.close();
		return true;
	}

	public boolean deleteComment(long cid) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		comment = session.get(blg_comments.class, cid);
		session.delete(comment);
		tr.commit();
		session.close();
		return true;
	}
}
