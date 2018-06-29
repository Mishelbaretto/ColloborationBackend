package com.tcs.colloboration;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.tcs.colloboration.dao.UserDAO;
import com.tcs.colloboration.model.User;

//@ComponentScan(basePackageClasses="com.tcs");
public class UserDAOTestCase {

	
	
	
	
	private static AnnotationConfigApplicationContext context;
	//we need to create instance of AnnotationConfigApplicationContext only once
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static User user;
	
	
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.tcs");
		context.refresh();
		
	userDAO=(UserDAO)	context.getBean("userDAO");
	user=(User)	context.getBean("user");
		
		
		
	}
	
	@Test
	public void addUserTestCase() {
		user.setEmailID("mish@gmail.com");
		user.setName("Mishel");
		user.setDetails("Manglore");
		user.setPassword("mish@123");
		user.setRole('A');
		user.setStatus('A');
		//boolean actual=userDAO.save(user);
		//userDAO.save(user);
		Assert.assertEquals("Add User Test Case",true,userDAO.save(user));
	}

	@Test
	public void updateUserTestCase() {
		//user=new User();
		user=userDAO.get("abhi@gmail.com");
		user.setRole('N');
		//user.setMobile("9999999999");
		boolean actual=userDAO.update(user);
		Assert.assertEquals("Update User", true,actual);
	}
	
	@Test
	public void getUserTestCase() {
		
		Assert.assertNotNull("Get User Test Case", userDAO.get("jaya@gmail.com"));
	}
	@Test
	public void validateUserTestCase() {
		Assert.assertNotNull("Validate Testcase",userDAO.validate("jaya@gmail.com", "jaya@123"));
	}
	@Test
	public void deleteUserTestCase() {
	boolean actual=	userDAO.delete("naina@gmail.com");
	Assert.assertEquals(true, actual);
	}
	@Test
	public void getAllUsers() {
	int actualSize=	userDAO.list().size();
	Assert.assertEquals(1, actualSize);
	}
}
