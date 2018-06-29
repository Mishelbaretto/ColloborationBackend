package com.tcs.colloboration.model;

import java.sql.Clob;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="c_job")
public class Job extends BaseDomain{

	
	    @Id
	   // @GeneratedValue(strategy=GenerationType.AUTO)
		private int	id;
		private String	title;
		private String	description;
		private String	qualification ;
		private int	salary ;
		private char status ;
			private int	no_of_openings;
			private	Date posted_date ;
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			public String getQualification() {
				return qualification;
			}
			public void setQualification(String qualification) {
				this.qualification = qualification;
			}
			public int getSalary() {
				return salary;
			}
			public void setSalary(int salary) {
				this.salary = salary;
			}
			public char getStatus() {
				return status;
			}
			public void setStatus(char status) {
				this.status = status;
			}
			public int getNo_of_openings() {
				return no_of_openings;
			}
			public void setNo_of_openings(int no_of_openings) {
				this.no_of_openings = no_of_openings;
			}
			public Date getPosted_date() {
				return posted_date;
			}
			public void setPosted_date(Date posted_date) {
				this.posted_date = posted_date;
			}
			

}
