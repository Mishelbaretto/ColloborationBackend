package com.tcs.colloboration.dao;

import java.util.List;

import com.tcs.colloboration.model.User;

public interface UserDAO {

	
	///registration
	
		public boolean save(User user);
		
		//update user details
		public boolean update(User user);
		
		public boolean delete(String emailID);
		
		//see/fetch/get the details
		
		public User   get(String emailID);
		
		public User getname(String name);
		//admin may fetch all the user details
		
		public  List<User>     list();
		
		//login - validation
		
		public User validate(String emailID,  String password);
		
	
	
}
