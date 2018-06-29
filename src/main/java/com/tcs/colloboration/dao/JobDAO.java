package com.tcs.colloboration.dao;

import java.util.List;

import com.tcs.colloboration.model.Job;
import com.tcs.colloboration.model.JobApplication;

public interface JobDAO {
//create a new job
	public boolean save(Job job);
	
	//admin may update the job details
	public boolean update(Job job);
	
	//admin will not delete the job
	//once the job is closed,admin will change status F/C
	
	//fetch aparticular job
	public Job get(int id);
	
	//fetching all jobs
	public List<Job> list();
	
	//fetch all the jobss based on status
	
	public List<Job> list(char status);
	
	
	
	public boolean isJobOpened(int id);
	
	
	public boolean deleteJob(int id);	
	
	
	//job application
	//----------------------------------------------------------------------------------
	//apply for a particular job
	public  boolean save(JobApplication jobApplication);
	
	//Admin can reject
	
	public  boolean update(JobApplication jobApplication);
	
	//admin want to know the list of user those applied for particular job
	
	public List<JobApplication> list(int jobID);
	
	//admin want to fetch all the details of particular job based on staus
	
	public List<JobApplication> list(int jobID ,char status);
	
	
	//if user alredy applied for the job,
	//it will return jobapplication
	//else return null
	public JobApplication get(String emailID,int jobID);         //methods which return bolean(i.e true or false) shud start whith is
	
//get all job applications applied by a particular user
	public List<JobApplication>  list(String emailID);
	
	
	//admin /user want to know whether job exist with particular status
	
	public Job get(int jobID,char status);
	
	
	public  boolean isJobAlreadyApplied(String emailID, int jobID);
}
