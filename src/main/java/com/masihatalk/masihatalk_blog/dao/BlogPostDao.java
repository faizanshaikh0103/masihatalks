package com.masihatalk.masihatalk_blog.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masihatalk.masihatalk_blog.entity.Blg_blog_post;
import com.masihatalk.masihatalk_blog.entity.Blg_blog_post_views;
import com.masihatalk.masihatalk_blog.entity.blg_comments;

@Repository
public class BlogPostDao {

	@Autowired
	private Blg_blog_post blog;
	@Autowired
	private SessionFactory sf;

	public String postblog(Blg_blog_post blg) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.save(blg);
		tr.commit();
		session.close();
		return "Data inserted Success";
	}

	public List<Blg_blog_post> getbloglist() {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Blg_blog_post.class);
		List<Blg_blog_post> list = cr.list();
		session.close();
		return list;
	}

	public Blg_blog_post getblogbyid(long id) {
		Session session = sf.openSession();
		Blg_blog_post post = session.get(Blg_blog_post.class, id);
		session.close();
		return post;
	}

	public List<Blg_blog_post> getBlogsbyUserId(String user_id) {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Blg_blog_post.class);
		cr.add(Restrictions.eq("user_id", user_id));
		cr.addOrder(Order.desc("blog_id"));
		List<Blg_blog_post> list = cr.list();
		session.close();
		return list;
	}

	public String updateblog(long bid, Blg_blog_post blg) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		blog = session.get(Blg_blog_post.class, bid);
		blog.setBlog_content(blg.getBlog_content());
		blog.setBlog_title(blg.getBlog_title());
		blog.setImage_url(blg.getImage_url());
		session.update(blog);
		tr.commit();
		session.close();
		return "Update successful...";
	}

	public boolean deleteblog(long bid) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		blog = session.get(Blg_blog_post.class, bid);
		Criteria cr = session.createCriteria(blg_comments.class);
		cr.add(Restrictions.eq("blog_id", blog.getBlog_id()));
		List<blg_comments> cmt_list = cr.list();
		for (blg_comments comments : cmt_list) {
			session.delete(comments);
		}
		session.delete(blog);
		tr.commit();
		session.close();
		return true;
	}

	public Blg_blog_post_views blogViewCount(long bid) {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Blg_blog_post_views.class);
		cr.add(Restrictions.eq("blog_id", bid));
		Blg_blog_post_views count = (Blg_blog_post_views) cr.uniqueResult();
		session.close();
		if (count != null) {
			return count;
		} else {
			return null;
		}

	}

	public boolean IncreaseViewCount(Blg_blog_post_views view) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Criteria cr = session.createCriteria(Blg_blog_post_views.class);
		cr.add(Restrictions.eq("blog_id", view.getBlog_id()));
		Blg_blog_post_views adview = (Blg_blog_post_views) cr.uniqueResult();
		if (adview != null) {
			System.out.println("View Debugging => " + adview);
			System.out.println("count debug => " + adview.getView_count());
			adview.setBlog_id(adview.getBlog_id());
			adview.setView_count(adview.getView_count() + 1);
			session.update(adview);
		} else {
			view.setView_count(1);
			session.save(view);
		}
		tr.commit();
		session.close();
		return true;
	}

	public boolean changedataType() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("ALTER TABLE blg_blog_post modify COLUMN blog_content TEXT, COLUMN image_url LONGBLOB");
		query.executeUpdate();
		tx.commit();
		session.close();
		return true;
	}

}
