package com.masihatalk.masihatalk_blog.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.masihatalk.masihatalk_blog.entity.blg_permissions;
import com.masihatalk.masihatalk_blog.additional.Pendingrequest;
import com.masihatalk.masihatalk_blog.entity.blg_comments;
import com.masihatalk.masihatalk_blog.entity.blg_permissions;

@Repository
public class PermissionDao {

	@Autowired
	private SessionFactory sf;
	
	
	public boolean sendRequest(blg_permissions request) {
		System.out.println("dao 1 => "+ request);
		Session session = sf.openSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			System.out.println("dao => "+request);
			session.save(request);
			tr.commit();
			return true;
		} catch(Exception e){
			if(tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	

	public List<Pendingrequest> getAllRequest() {
		Session session = sf.openSession();
		System.out.println("start");
		String sql = "select pf.user_id, pf.user_name, pf.email, pr.message, pr.access, pr.status from blg_user_profile pf Inner join blg_permissions pr on pf.user_id = pr.user_id where pr.status = 'pending'";
		try {
			NativeQuery query = session.createNativeQuery(sql);
			List<Object[]> st = query.getResultList();
			List<Pendingrequest> pr = new ArrayList<Pendingrequest>();
			for (Object[] req : st) {
				Pendingrequest pp = new Pendingrequest();
				pp.setUser_id( (String) req[0]);
				pp.setUser_name( (String) req[1]);
				pp.setEmail( (String) req[2]);
				pp.setMessage((String) req[3]);
				pp.setAccess((String) req[4]);
				pp.setStatus((String) req[5]);
				
				pr.add(pp);
			}
			return pr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			System.out.println("end");
			session.close();
		}
	}

	public boolean updateStatus(blg_permissions request) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		try {
			Criteria cr = session.createCriteria(blg_permissions.class);
			cr.add(Restrictions.eq("user_id", request.getUser_id()));
			blg_permissions obj = (blg_permissions) cr.uniqueResult();
			obj.setStatus(request.getStatus());
			obj.setMessage(request.getMessage());
			obj.setAccess(request.getAccess());
			session.update(obj);
			return true;
		} catch (Exception e ) {
			return false;
		} finally {
			tr.commit();
			session.close();
		}
	
	}

	public List<Pendingrequest> getAllacceptrequest() {
		Session session = sf.openSession();
		String sql = "select pf.user_name, pf.user_id, pf.email, pr.status, pr.message, pr.access from blg_user_profile pf inner join blg_permissions pr on pf.user_id= pr.user_id where status = 'approved'";
		try {
			NativeQuery query = session.createNativeQuery(sql);
			List<Object[]>  list = query.getResultList();
			List<Pendingrequest> pr = new ArrayList<>();
			for(Object[] obj : list) {
				Pendingrequest req = new Pendingrequest();
				req.setUser_name((String) obj[0]);
				req.setUser_id( (String) obj[1]);
				req.setEmail((String) obj[2]);
				req.setStatus((String) obj[3]);
				req.setMessage((String) obj[4]);
				req.setAccess( (String) obj[5]);
				
				pr.add(req);
			}
			return pr;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}


	public boolean checkExist(String user_id) {
		Session session = sf.openSession();
		try {
			Criteria cr = session.createCriteria(blg_permissions.class);
			cr.add(Restrictions.eq("user_id", user_id));
			blg_permissions data = (blg_permissions) cr.uniqueResult();
			if(data != null) {
				return true;
			} else {
				return false;
			}
		} 
		catch (Exception e) {
			return false;
		}
		finally {
			session.close();
		}
	}


	public Pendingrequest getRequestbyId(String user_id) {
		Session session = sf.openSession();
		try {
			String sql = "select pr.id, pf.user_id, pf.user_name, pf.email, pr.message, pr.access, pr.status from blg_user_profile pf Inner join blg_permissions pr on pf.user_id = pr.user_id where pr.user_id = :uid";
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("uid", user_id);
			Object obj = query.getSingleResult();
			Object[] result = (Object[]) obj;
			Pendingrequest prequest = new Pendingrequest();
//			prequest.setId((BigInteger) result[0]).longValueExact();
			prequest.setUser_id((String) result[1]);
			prequest.setUser_name((String) result[2]);
			prequest.setEmail((String) result[3]);
			prequest.setMessage((String) result[4]);
			prequest.setAccess((String) result[5]);
			prequest.setStatus((String) result[6]);
			return prequest;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			session.close();
		}
	}


	public blg_permissions getUserpermission(String user_id) {
		Session session = sf.openSession();
		try {
			Criteria cr = session.createCriteria(blg_permissions.class);
			cr.add(Restrictions.eq("user_id", user_id));
			blg_permissions per = (blg_permissions) cr.uniqueResult();
			return per;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
	
}
