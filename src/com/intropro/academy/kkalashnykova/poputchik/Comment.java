package com.intropro.academy.kkalashnykova.poputchik;

import java.util.Date;

public class Comment {
	
	private Profile owner;
	private String body = "";
	private Date dateTime;
	
	public void createComment(){
		
	}
	
	public void viewListOfComments(){
		
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
}
