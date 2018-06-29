package com.tcs.colloboration.model;

import javax.persistence.Transient;

public class BaseDomain {

	//this field is required for all the domain clases
		//there is no field in db table
		@Transient
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	
	
}
