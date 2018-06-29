package com.tcs.colloboration.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.colloboration.dao.FriendDAO;
import com.tcs.colloboration.dao.UserDAO;
import com.tcs.colloboration.model.Friend;
import com.tcs.colloboration.model.User;


@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public List<Friend> showFriendList(String emailID) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (emailID=:myemailID or friendemailID=:friendemail) and status='A'");
		query.setParameter("myemailID",emailID);
		query.setParameter("friendemail", emailID);
		List<Friend> listFriends=(List<Friend>)query.list();
		return listFriends;
	}

	public List<Friend> showPendingRequest(String emailID) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendemailID=:myemailID and status='P'");
		query.setParameter("myemailID",emailID);
		List<Friend> listFriends=(List<Friend>)query.list();
		return listFriends;
	}
	
	public List<User> showSuggestedFriend(String emailID) {
		/*Session session=sessionFactory.openSession();
		SQLQuery sqlQuery=session.createSQLQuery("select emailID from c_user where emailID not in(select friendemailID from c_friend where emailID='"+emailID+"') and emailID!='"+emailID+"' and role!='A'");
		List<String> listUsers=(List<String>)sqlQuery.list();
		ArrayList<User> listUser=new ArrayList<User>();
		int i=0;
		while(i<listUsers.size())
		{
			User user=session.get(User.class, listUsers.get(i));
			listUser.add(user);
			i++;
		}
		
		return listUser;*/
		
		List<String> users = sessionFactory.getCurrentSession().createSQLQuery("select emailID from c_user where emailID not in(select "
				+ "friendemailID from c_friend where emailID = '"+emailID+"' and status = 'A' UNION ALL select emailID from c_friend "
						+ "where friendemailID = '"+emailID+"' and status = 'A') AND emailID not in(select friendemailID from c_friend where "
								+ "emailID = '"+emailID+"' and status = 'P' UNION ALL select emailID from c_friend where friendemailID = '"+emailID+"'"
								+ " and status = 'P') and emailID!='"+emailID+"'").list();
		List<User> suggestedPeople = new ArrayList<User>();
		int i = 0;
		while(i < users.size())
		{
			User user = sessionFactory.getCurrentSession().get(User.class, users.get(i));
			suggestedPeople.add(user);
			i++;
		}
		return suggestedPeople;
		/*Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("select emailID from c_user where emailID not in (select friendemailID from c_friend where emailID='"+emailID+"')and emailID!='"+emailID+"' and role!='A'");
		List<Object> suggestFriendname=(List<Object>)query.list();
		List<User> suggestFriendList=new ArrayList<User>();
		int i=0;
		while(i<suggestFriendname.size())
		{
			User userDetail=(User) session.get(User.class, (String)suggestFriendname.get(i));
			suggestFriendList.add(userDetail);
			i++;
		}
		return suggestFriendList;*/
	}

	/*public List<Friend> showFriendList(String emailId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (emailID=:myemailID or friendemailID=:friendemail) and status='A'");
		query.setParameter("myemailId",emailID);
		query.setParameter("friendemail", emailID);
		List<Friend> listFriends=(List<Friend>)query.list();
		return listFriends;
	}
	*/

	/*public List<Friend> showFriend(String emailID) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (emailID=:myemailID or friendemailID=:friendemail) and status='A'");
		query.setParameter("myemailId",emailID);
		query.setParameter("friendemail", emailID);
		List<Friend> listFriends=(List<Friend>)query.list();
		return listFriends;
	}
*/
	/*public List<Friend> showPendingRequest(String emailID) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendemailID=:myemailID and status='P'");
		query.setParameter("myemailId",emailID);
		List<Friend> listFriends=(List<Friend>)query.list();
		return listFriends;
	}

	public List<User> showSuggestedFriend(String emailID) {
		Session session=sessionFactory.openSession();
		SQLQuery sqlQuery=session.createSQLQuery("select emailID from User where emailID not in(select friendemailID from Friend where emailID='"+emailID+"') and emailID!='"+emailID+"'");
		List<String> listUsers=(List<String>)sqlQuery.list();
		ArrayList<User> listUser=new ArrayList<User>();
		int i=0;
		while(i<listUsers.size())
		{
			User user=session.get(User.class, listUsers.get(i));
			listUser.add(user);
			i++;
		}
		
		return listUser;
	}*/


	public boolean sendFriendRequest(Friend friend) {
		try
		{
			
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	public boolean acceptFriendRequest(int friendID) {
		try
		{
			/*Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendID);
			System.out.println("Login Name"+friend.getEmailID());
			friend.setStatus("A");
			session.update(friend);
			System.out.println("Updated");
			session.flush();
			session.close();
			return true;*/
			
			Session session=sessionFactory.getCurrentSession();
			Friend friend=(Friend)session.get(Friend.class,friendID);
			friend.setStatus("A");
			session.update(friend);		
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}

	
	public boolean deleteFriendRequest(int friendID) {
		try
		{
			//Session session=sessionFactory.openSession();
			Session session=sessionFactory.getCurrentSession();		
			Friend friend=(Friend)session.get(Friend.class, friendID);
			session.delete(friend);
			System.out.println("del");
		//	session.flush();
			//session.close();
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}

	
	
	

	

}
