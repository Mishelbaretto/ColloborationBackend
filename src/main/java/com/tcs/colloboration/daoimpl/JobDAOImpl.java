package com.tcs.colloboration.daoimpl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.colloboration.dao.JobDAO;
import com.tcs.colloboration.dao.UserDAO;
import com.tcs.colloboration.model.Job;
import com.tcs.colloboration.model.JobApplication;
@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Autowired
	private UserDAO userDAO;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//this method goeing to return max job id of all records
	//if records exist
	//else
	//return 100
	private int getMaxJobID() {
		int maxValue=100;
	try {
		 maxValue=(Integer)	getCurrentSession().createQuery("select max(id) from Job").uniqueResult();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 100;
	}
	return maxValue;
	}
	
	private int getMaxJobApplicationID() {
		int maxValue=100;
	try {
		 maxValue=(Integer)	getCurrentSession().createQuery("select max(id) from JobApplication").uniqueResult();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 100;
	}
	return maxValue;
	}
	
	public boolean save(Job job) {
		try {
			job.setId(getMaxJobID()+1);
			job.setPosted_date(new Date(System.currentTimeMillis()));
			job.setStatus('N');
			getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Job job) {
		try {
			getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Job get(int jobID) {
		 return (Job)getCurrentSession().createCriteria(Job.class)
		.add(Restrictions.eq("id", jobID)).uniqueResult();
		// TODO Auto-generated method stub
		
	}

	public List<Job> list() {
		return getCurrentSession().createQuery("from Job").list();
	}

	public List<Job> list(char status) {
		return getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("status", status)).list();

	}

	public boolean save(JobApplication jobApplication) {
		try {
			if(!isJobOpened(jobApplication.getJobID())) {
				return false;
			}
			
			//if already applied u cant apply again
			if (isJobAlreadyApplied(jobApplication.getEmailID(), jobApplication.getJobID())) {
				return false;
			}
			
			//if user does not exist u cant apply
			
			if(userDAO.get(jobApplication.getEmailID())==null) {
				return false;
			}
			
			//if job des no exist u cant apply
			if(get(jobApplication.getJobID())==null) {
				return false;
			}
			
			
			jobApplication.setId(getMaxJobApplicationID()+1);
			jobApplication.setStatus('N');
			jobApplication.setApplied_date(new Date(System.currentTimeMillis()));
			getCurrentSession().save(jobApplication);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(JobApplication jobApplication) {
		try {
			getCurrentSession().update(jobApplication);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	}

	public List<JobApplication> list(int jobID) {
		return getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("id", jobID)).list();
	}

	public List<JobApplication> list(int jobID, char status) {
		return getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("id", jobID))
				.add(Restrictions.eq("status", status)).list();
	}

	
	//this methid will return true, if the job with id exist and status is open
	//else return false
	public boolean isJobOpened(int id) {
	Job	job=(Job) getCurrentSession().createCriteria(Job.class)
		.add(Restrictions.eq("id", id)).uniqueResult();
	if(job!=null && job.getStatus()=='N') {
		return true;
	}
	return false;
	}

	
	//this method will return true if job already applied with this emailid 
	//else return false
	/*public JobApplication get(String emailID, int jobID) {//where condition parameteres are there so create criteria
		
		//select * from jobapplication where emailid=? and jobID=?
	JobApplication jobApplication 	= (JobApplication) getCurrentSession().createCriteria(JobApplication.class)
			.add(Restrictions.eq("emailID", emailID))
			.add(Restrictions.eq("jobID", jobID)).uniqueResult();//key is the field vlue of domain object and second parameter is what we passed from the test case
	
	if(jobApplication==null) {
		return false;
	}
	return true;
	
	
	
	}*/
//return list of job applications applied by a particular user
	//if no job applied by this particular user return empty string
	public List<JobApplication> list(String emailID) {
		
		Session session=sessionFactory.getCurrentSession();
		List<JobApplication> lapjob=null;
		Query query= session.createQuery("from Job where id in(select jobID from JobApplication where emailID=:currentuser) ");
		query.setParameter("currentuser", emailID);
		lapjob=query.list();
		return lapjob;
	}

	
	//this method will return job if the job exists with yhis id and status
	public Job get(int jobID, char status) {
	return	(Job) getCurrentSession().createCriteria(Job.class)
		.add(Restrictions.eq("jobID",jobID))
		.add(Restrictions.eq("status", status)).uniqueResult();
	}

	/*public JobApplication get(String emailID, int jobID) {
		// TODO Auto-generated method stub
		return null;
	}*/

	public boolean isJobAlreadyApplied(String emailID, int jobID) {
		//select * from JobApplication where emailID = ? and jobID = ?
				JobApplication jobApplication = (JobApplication) getCurrentSession()
						.createCriteria(JobApplication.class)
						.add(Restrictions.eq("emailID", emailID))
						.add(Restrictions.eq("jobID", jobID)).uniqueResult();

				if (jobApplication == null) {
					return false;
				}
				return true;

			}

	public JobApplication get(String emailID, int jobID) {
		//select * from JobApplication where emailID = ? and jobID = ?
		return (JobApplication) getCurrentSession()
				.createCriteria(JobApplication.class)
				.add(Restrictions.eq("emailID", emailID))
				.add(Restrictions.eq("jobID", jobID)).uniqueResult();


	}

	public boolean deleteJob(int id) {
		try {
			sessionFactory.getCurrentSession().delete(get(id));
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	}


