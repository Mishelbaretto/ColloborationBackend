package com.tcs.colloboration.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.colloboration.dao.ProfilePictureDAO;
import com.tcs.colloboration.model.ProfilePicture;
import com.tcs.colloboration.model.User;
@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean save(ProfilePicture profilePicture) {
		/*System.out.println("Profile Loginname : "+profilePicture.getEmailID());
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(profilePicture);
		session.flush();
		session.close();*/
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(profilePicture);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public ProfilePicture getProfilePicture(String emailID) {
		/*Session session=sessionFactory.openSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, name);
		session.close();
		return  profilePicture;*/
		return	(ProfilePicture) sessionFactory.getCurrentSession().get(ProfilePicture.class,emailID);
	}

}
