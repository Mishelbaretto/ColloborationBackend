package com.tcs.colloboration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="c_friend")
@SequenceGenerator(name="myfriendidseq",sequenceName="friendidseq")
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="myfriendidseq")

	int friendID;
	String emailID;
	String friendemailID;
	String status;//P,A  p-pending,a-accept,s-suggested friend
	public int getFriendID() {
		return friendID;
	}
	public void setFriendID(int friendID) {
		this.friendID = friendID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getFriendemailID() {
		return friendemailID;
	}
	public void setFriendemailID(String friendemailID) {
		this.friendemailID = friendemailID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
