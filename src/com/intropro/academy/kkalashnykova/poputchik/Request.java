package com.intropro.academy.kkalashnykova.poputchik;

public class Request {
	
	private Profile owner;
	private RequestStatus status;
	
	public void notifyRequest(){
		//TODO NOT implemented yet
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus initial) {
		this.status = initial;
	}
	
	

}
