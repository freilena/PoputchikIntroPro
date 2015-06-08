package com.intropro.academy.kkalashnykova.poputchik;

public class Request {
	
	private Profile owner;
	private String status;
	
	public void notifyRequest(){
		//NOT implemented yet
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
