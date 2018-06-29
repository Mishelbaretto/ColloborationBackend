package com.tcs.colloboration;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.colloboration.dao.FriendDAO;
import com.tcs.colloboration.model.Friend;
import com.tcs.colloboration.model.User;



public class FriendDAOTestCase {


	
	

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static FriendDAO friendDAO;
	
	@Autowired
	private static Friend friend;
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.tcs");
		context.refresh();
		
		friendDAO=(FriendDAO)	context.getBean("friendDAO");
		friend=(Friend)	context.getBean("friend");
		
			
	}
	
		
		
		@Test
		public void sendFriendTest()
		{
			Friend friend=new Friend();
			
			friend.setEmailID("seema@gmail.com");
			friend.setFriendemailID("meena@gmail.com");
			assertTrue("Problem in Sending Friend Request",friendDAO.sendFriendRequest(friend));
		}
		
		@Test
		public void acceptFriendRequest()
		{
			Friend friend=new Friend();
			assertTrue("Problem in Accepting Friend Request",friendDAO.acceptFriendRequest(971));
		}
		
		@Test
		public void deleteFriendRequest()
		{
			
			assertTrue("Problem in Deleting Friend Request",friendDAO.deleteFriendRequest(974));
		}
		
		@Test
		public void suggestFriendRequest()
		{
			List<User> listUser=friendDAO.showSuggestedFriend("seema@gmail.com");
			
			assertTrue("Problem in Listing the Suggested Friends",listUser.size()>0);
			
			for(User user:listUser)
			{
				System.out.println("Login Name:"+user.getEmailID());
			}
		}
		
		@Test
		public void showFriendList()
		{
			List<Friend> listFriend=friendDAO.showFriendList("seema@gmail.com");
			assertTrue("Problem in Listing Friend",listFriend.size()>0);
			for(Friend friend:listFriend)
			{
				System.out.println("Login Name:"+friend.getEmailID()+"Friend Name:"+friend.getFriendemailID());
			}
		}
		
		@Test
		public void showPendingRequest()
		{
			List<Friend> listFriend=friendDAO.showPendingRequest("abhi@gmail.com");
			
			assertTrue("Problem in Listing Friend",listFriend.size()>0);
			
			for(Friend friend:listFriend)
			{
				System.out.println("Login Name:"+friend.getEmailID()+"Friend Name:"+friend.getFriendemailID());
			}
			
		}
	

	
	
	
	
	

}
