package com.tcs.colloboration;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.colloboration.dao.ForumDAO;
import com.tcs.colloboration.dao.UserDAO;
import com.tcs.colloboration.model.Blog;
import com.tcs.colloboration.model.Forum;
import com.tcs.colloboration.model.User;

public class ForumDAOTestCase {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static ForumDAO forumDAO;
	
	@Autowired
	private static Forum forum;
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.tcs");
		context.refresh();
		
		forumDAO=(ForumDAO)	context.getBean("forumDAO");
		forum=(Forum)	context.getBean("forum");
		
			
	}

	@Test
	public void addForum() {
		Forum forum=new Forum();
		forum.setForumName("Java 8");
		forum.setForumContent("Java 8 features");
		forum.setCreatedDate(new java.util.Date());
		forum.setLikes(0);
		forum.setEmailID("seema@gmail.com");
		forum.setStatus("NA");
		
		assertTrue("Problem in adding Forum",forumDAO.addForum(forum));
		
	}
	@Test
	public void approveForum() {//952 is the no generated in db when u run addForum test case
		Forum forum=forumDAO.getForum(952);
		assertTrue("Problem in approving Forum",forumDAO.approveForum(forum));
	};
	
}
