package com.tcs.colloboration;

import static org.junit.Assert.*;

import org.junit.Test;


	
	

	import static org.junit.Assert.assertTrue;

	import java.util.List;

	import org.junit.BeforeClass;
	import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.colloboration.dao.BlogDAO;
import com.tcs.colloboration.dao.ForumDAO;
import com.tcs.colloboration.model.Blog;
import com.tcs.colloboration.model.BlogComment;
import com.tcs.colloboration.model.Forum;

	

	public class BlogDAOTestCase 
	{
		
		private static AnnotationConfigApplicationContext context;
		
		@Autowired
		private static BlogDAO blogDAO;
		
		@Autowired
		private static Blog blog;
		
		@BeforeClass
		public static void initialize()
		{
			context=new AnnotationConfigApplicationContext();
			context.scan("com.tcs");
			context.refresh();
			
			blogDAO=(BlogDAO)	context.getBean("blogDAO");
			blog=(Blog)	context.getBean("blog");
			
		}
		
		@Test
		public void addBlogTest()
		{
			Blog blog=new Blog();
				
			blog.setBlogName("Hibernate Framework");
			blog.setBlogContent("Blog Specific to Hibernate Framework and Related Concepts");
			blog.setLikes(0);
			blog.setEmailID("seema@gmail.com");
			blog.setStatus("A");
			blog.setCreateDate(new java.util.Date());
			
			assertTrue("Problem in Blog Insertion",blogDAO.addBlog(blog));
		}

		
		@Test
		public void deleteBlogTest()
		{
			assertTrue("Problem in Blog Deletion:",blogDAO.deleteBlog(1022));
		}
		
		@Test
		public void rejectBlogTest()
		{
			Blog blog=blogDAO.getBlog(953);
			assertTrue("Proble in Blog Rejection:",blogDAO.rejectBlog(blog));
		}
		
		@Test
		public void approvalBlogTest()
		{
			Blog blog=blogDAO.getBlog(953);
			assertTrue("Proble in Blog Rejection:",blogDAO.approveBlog(blog));
		}

		@Test
		public void listBlogsByUserTest()
		{
			List<Blog> listBlogs=blogDAO.listApprovedBlogs(); //Returns an Collection
			assertTrue("Problem in Listing Blogs",listBlogs.size()>0);
			
			for(Blog blog:listBlogs)
			{
				System.out.print(blog.getBlogName()+":::");
				System.out.print(blog.getBlogContent()+":::");
				System.out.println(blog.getEmailID());
			}
		}
		
		@Test
		public void incrementBlogLikeTest()
		{
			Blog blog=blogDAO.getBlog(953);
			assertTrue("Problem in Increment of Like:",blogDAO.incrementLike(blog));
		}
		
		@Test
		public void addCommentTest()
		{
			BlogComment comment=new BlogComment();
			comment.setCommentText("The Blog is gives overall information:");
			comment.setEmailID("meena@gmail.com");
			comment.setBlogId(953);
			comment.setCommentDate(new java.util.Date());
			
			assertTrue("Problem in Insertion of Blog Comment",blogDAO.addBlogComment(comment));
		}
		
		@Test
		public void listAllBlogCommentsTest()
		{
			List<BlogComment> listBlogComments=blogDAO.listBlogComments(953);
			assertTrue("Problem in Retrieving all the BlogComments:",listBlogComments.size()>0);
			
			for(BlogComment blogComment:listBlogComments)
			{
				System.out.print(blogComment.getBlogId()+":::");
				System.out.print(blogComment.getCommentText()+":::");
				System.out.println(blogComment.getEmailID());
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
	

	

}
