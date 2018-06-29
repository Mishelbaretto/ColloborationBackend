package com.tcs.colloboration;



import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.colloboration.dao.JobDAO;
import com.tcs.colloboration.model.Job;
import com.tcs.colloboration.model.JobApplication;


public class JobDAOTestCase {

	private static AnnotationConfigApplicationContext context;
	//we need to create instance of AnnotationConfigApplicationContext only once
	@Autowired
	private static JobDAO jobDAO;
	
	@Autowired
	private static Job job;
	
	@Autowired
	private static JobApplication jobApplication;
	
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.tcs");
		context.refresh();
		
	jobDAO=(JobDAO)	context.getBean("jobDAO");
	job=(Job)	context.getBean("job");
	jobApplication = (JobApplication) context.getBean("jobApplication");
		
		
	}
	
	@Test
	public void saveJobTestCase() {
		job.setDescription("This is programmer job");
		job.setNo_of_openings(10);
		job.setQualification("BE");
		job.setSalary(20000);
		job.setTitle("Programmer");
		
		Assert.assertEquals("Save user test case",true, jobDAO.save(job));
		
	}
	@Test
	public void updateTestCaseSucess() {
		job=jobDAO.get(101);
		job.setStatus('N');
		//job.setQualification("B.Eng");
		Assert.assertEquals(true, jobDAO.update(job));
	}
	
	@Test
	public void updateTestCaseFailure() {
		job=jobDAO.get(105);
		Assert.assertNotNull(job);
		job.setStatus('N');
		//job.setQualification("B.Eng");
		Assert.assertEquals(true, jobDAO.update(job));
	}
	
	@Test
	public void getJobSuccessTestCase() {
		Assert.assertNotNull(jobDAO.get(101));
	}
	@Test
	public void getJobFailureTestCase() {
		Assert.assertNotNull(jobDAO.get(108));
	}
	@Test
	public void getAllJobs() {
		Assert.assertEquals(8, jobDAO.list().size());
	}
	
	@Test
	public void closeJobTestCase() {
	job=	jobDAO.get(101);
	job.setStatus('C');//c--for closed 
		Assert.assertEquals(true, jobDAO.update(job));
	}

	@Test
	public void isJobOpenedSucessTestCase() {
	Assert.assertEquals(true,	jobDAO.isJobOpened(101));
	}
	
	@Test
	public void isJobOpenedFailureTestCase() {
	Assert.assertEquals(true,	jobDAO.isJobOpened(103));
	}
	
	
	
	
	
	
///=====================================================
	//JOB APPLICATION related test cases
	@Test
	public void applyForAJobSucessTestCase() {
		jobApplication.setEmailID("seema@gmail.com");
		jobApplication.setJobID(102);
		Assert.assertEquals(true,jobDAO.save(jobApplication));
	}
	
	
	@Test
	public void applyForAJobFailureTestCase() {
		jobApplication.setEmailID("jaya@123");
		jobApplication.setJobID(101);
		Assert.assertEquals(false,jobDAO.save(jobApplication));
	}
	
	@Test
	public void isJobAlredayAppliedSucessTestCase() {
		Assert.assertEquals(true, jobDAO.isJobAlreadyApplied("jaya@gmail.com", 103));
	}
	
	@Test
	public void isJobAlredayAppliedFailureTestCase() {
		Assert.assertEquals(false, jobDAO.isJobAlreadyApplied("jaya@gmail.com", 104));
	}
	
	
	
	
	
	
	
	
	
}
