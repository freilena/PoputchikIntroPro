package com.intropro.academy.kkalashnykova.poputchik;

import java.util.Date;

public class Message {
	
	private Profile from;
	private Profile to;
	private Date dateTime;
	private String body;
	
	public void sendMessage(){
		
	}
	
	public void notifyReviever(){
		
	}

	public Profile getFrom() {
		return from;
	}

	public void setFrom(Profile from) {
		this.from = from;
	}

	public Profile getTo() {
		return to;
	}

	public void setTo(Profile to) {
		this.to = to;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
}
