package com.tcs.colloboration.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.colloboration.dao.ForumDAO;
import com.tcs.colloboration.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	public List<Forum> listApprovedForums() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum where status='A' ");
		List<Forum> listForums=query.list();
		return listForums;
	}

	public List<Forum> listAllForums() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum ");
		List<Forum> listForums=query.list();
		return listForums;
	}

	public boolean approveForum(Forum forum) {
		try {
		//Session session=sessionFactory.openSession();
		//Forum forum=this.getForum(forumId);
		forum.setStatus("A");
		sessionFactory.getCurrentSession().update(forum);
		//session.update(forum);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	
	public boolean rejectForum(Forum forum) {
		try {
			/*Session session=sessionFactory.openSession();
			Forum forum=this.getForum(forumId);*/
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			//session.update(forum);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;

			}
	}

	public Forum getForum(int forumId) {
		Session session=sessionFactory.openSession();
		Forum forum=(Forum)session.get(Forum.class, forumId);
		session.close();
		return forum;

	}

	public boolean incLike(int forumId) {
		// TODO Auto-generated method stub
		
		
		try {
			Session session=sessionFactory.getCurrentSession();
			Forum forum=this.getForum(forumId);
			int like=forum.getLikes();
			like++;
			forum.setLikes(like);
			session.update(forum);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
