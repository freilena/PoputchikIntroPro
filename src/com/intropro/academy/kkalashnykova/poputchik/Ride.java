package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ride {
	
	private String start;
	private String finish;
	private Date dateTime;
	private Profile owner;
	private String status;
	private List <Comment> comments = new ArrayList<Comment>();
	private List <Request> requests = new ArrayList<Request>();
	
	private Ride(){
		
	}
	
	public static Ride createRide(String start, String finish, Date dateTime, Profile owner){   //factory method
		Ride ride = new Ride();
		ride.setStart(start);
		ride.setFinish(finish);
		ride.setDateTime(dateTime);
		ride.setOwner(owner);
		ride.setStatus("active");
		return ride;		
		
	}
	
	public Request createRequest(Profile owner){
		Request request = new Request();
		request.setOwner(owner);
		request.setStatus("initial");
		requests.add(request);
		return request;
	}
	
	public boolean deleteRequest(Request request){
		if(requests.contains(request)){
			requests.remove(request);
			return true;
		}
		else{
			//to do some code here
			return false;
		}
	}
	
	public Request searchRequest(Profile requestOwner){
		Request request = null;	
		for (Request current : requests){
			if(current.getOwner().equals(requestOwner)){
				request = current;	
				break;
			}
		}
		if(request != null){
			return request;
		}
		else{
			throw new PoputchikDomainObjectDoesnotExistException();
		}
	}
	
	// нужен ли такой метод? или просто где-то в коде будет вызываться серч и потом сеттер? как правильно?
	// ?????????????????????
	public void confirmRequest(Profile requestOwner){
		Request request = this.searchRequest(requestOwner);
		request.setStatus("confirmed");
	}
	
	// нужен ли такой метод? или просто где-то в коде будет вызываться серч и потом сеттер? как правильно?
	// ?????????????????????
	public void denyRequest(Profile requestOwner){
		Request request = this.searchRequest(requestOwner);
		request.setStatus("denied");
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((finish == null) ? 0 : finish.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ride other = (Ride) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (finish == null) {
			if (other.finish != null)
				return false;
		} else if (!finish.equals(other.finish))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
}
