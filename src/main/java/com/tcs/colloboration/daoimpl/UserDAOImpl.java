package com.tcs.colloboration.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.tcs.colloboration.dao.UserDAO;
import com.tcs.colloboration.model.User;
@Repository("userDAO")
@Transactional
//@EnableTransactionManagement
public class UserDAOImpl  implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private User user;
	

	public boolean save(User user) {
		try {
			if(user.getRole()==null || user.getRole()==' ') {
			user.setRole('S');
			}
			user.setStatus('N');
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	
    //will return user object if the record exsits
	public User get(String emailID) {
		return	(User) sessionFactory.getCurrentSession().get(User.class,emailID);
	}

	public List<User> list() {
		return	sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public User validate(String emailID, String password) {
		return (User) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("emailID", emailID))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	public boolean delete(String emailID) {
		try {
			//before dekete,check wheteher the record exust or not
			user=	get(emailID);
			//if record does nt exist return false
		if(user==null) {
			return false;
		}
			sessionFactory.getCurrentSession().delete( user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public User getname(String name) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where name = '"+name+"'").uniqueResult();
	}

}
