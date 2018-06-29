package com.tcs.colloboration.daoimpl;
	import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;
	

import com.tcs.colloboration.dao.BlogDAO;
import com.tcs.colloboration.model.Blog;
import com.tcs.colloboration.model.BlogComment;

	

    @Transactional
	@Repository("blogDAO")
	public class BlogDAOImpl implements BlogDAO 
	{

		@Autowired
		SessionFactory sessionFactory;
		
		
		public boolean addBlog(Blog blog) 
		{
			try
			{
				blog.setCreateDate(new java.util.Date());
				blog.setLikes(0);
				blog.setStatus("NA");

				
				sessionFactory.getCurrentSession().save(blog);
				return true;
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean deleteBlog(int blogId) 
		{
			try
			{
				Blog blog=(Blog)sessionFactory.getCurrentSession().get(Blog.class,blogId);
				sessionFactory.getCurrentSession().delete(blog);
				return true;
			}
			catch(HibernateException e)
			{
			return false;
			}
		}
		
		public boolean updateBlog(Blog blog) {
			try
			{
				sessionFactory.getCurrentSession().update(blog);
				return true;
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			return false;
			}
		}

		public List<Blog> listApprovedBlogs() {
			try
			{
				Session session=sessionFactory.openSession(); //creating a session object
				Query query=session.createQuery("from Blog where status='A'"); //Creating a Query object
				List<Blog> listBlogs=query.list(); //calling list() method of query object which returns a list
				return listBlogs;
			}
			catch(HibernateException e)
			{
				return null;
			}
		}
		
		public boolean approveBlog(Blog blog) 
		{
			try
			{
				blog.setStatus("A"); //Specifying the status of the Blog as A which means Approved
				sessionFactory.getCurrentSession().update(blog); //updating the blog
				return true;
			}
			catch(HibernateException e)
			{
			return false;
			}
		}
		
		public boolean rejectBlog(Blog blog) 
		{
			try
			{
				blog.setStatus("NA");
				sessionFactory.getCurrentSession().update(blog); //updating blog
				return true;
			}
			catch(HibernateException e)
			{
			return false;
			}
		}

		public Blog getBlog(int blogId) {
			try
			{
				Session session=sessionFactory.openSession();
				Blog blog=(Blog)session.get(Blog.class, blogId);
				return blog;
			}
			catch(HibernateException e)
			{
				return null;
			}
		}

		public List<Blog> listAllBlogs() 
		{
			try
			{
				Session session=sessionFactory.openSession();
				Query query=session.createQuery("from Blog");
				List<Blog> listBlogs=query.list();
				return listBlogs;
			}
			catch(HibernateException e)
			{
				return null;
			}
		}
		
		
		public boolean incrementLike(Blog blog) {
			try
			{
				int likes=blog.getLikes();
				likes++;
				blog.setLikes(likes);
				sessionFactory.getCurrentSession().update(blog); //updating the blog
				return true;
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			return false;
			}
		}
		
		
		public boolean addBlogComment(BlogComment blogComment) 
		{
			try
			{
				blogComment.setCommentDate(new java.util.Date());
				
				sessionFactory.getCurrentSession().save(blogComment);
				return true;
			}
			catch(HibernateException e)
			{
			System.out.println(e);
			return false;
			}
		}
		
		public boolean deleteBlogComment(BlogComment blogComment) 
		{
			try
			{
				sessionFactory.getCurrentSession().delete(blogComment);
				return true;
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			return false;
			}
		}
		public BlogComment getBlogComment(int commentId) 
		{
			try
			{
				Session session=sessionFactory.openSession();
				BlogComment blogComment=(BlogComment)session.get(BlogComment.class,commentId);
				return blogComment;
			}
			catch(HibernateException e)
			{
				return null;
			}
		}
		public List<BlogComment> listBlogComments(int blogId) 
		{
			return sessionFactory.getCurrentSession().createCriteria(BlogComment.class).add(Restrictions.eq("blogId", blogId)).list();
		}
		

	

}
