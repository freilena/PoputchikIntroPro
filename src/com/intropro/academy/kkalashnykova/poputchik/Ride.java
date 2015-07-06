package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ride {

	private String start;
	private String finish;
	private Date dateTime;
	private Profile owner;
	private RideStatus status;
	private List<Comment> comments = new ArrayList<Comment>();
	private List<Request> requests = new ArrayList<Request>();
	private static Logger log = LogManager.getLogger(RideList.class);

	private Ride() {

	}

	public static Ride createRide(String start, String finish, Date dateTime, Profile owner) {
		log.info("createRide() Ride started.");
		Ride ride = new Ride();
		ride.setStart(start);
		ride.setFinish(finish);
		ride.setDateTime(dateTime);
		ride.setOwner(owner);
		ride.setStatus(RideStatus.active);
		log.info("createRide() Ride finished.");
		return ride;

	}

	public Request createRequest(Profile owner) throws PoputchikAlreadyExistsException {
		log.info("createRequest() started.");
		try {
			getRequest(owner);
		} catch (PoputchikDoesNotExistException e) {
			Request request = new Request();
			request.setOwner(owner);
			request.setStatus(RequestStatus.initial);
			requests.add(request);
			log.info("Request created successfully.");
			return request;
		}
		log.error("Error in createRequest: Failed to create new request from " + owner.getFirstName() + " " + owner.getLastName() + ". Request already exist.");
		throw new PoputchikAlreadyExistsException("Failed to create new request: Request already exist.");
	}

	public boolean deleteRequest(Request request) {
		log.info("deleteRequest() started.");
		if (requests.contains(request)) {
			requests.remove(request);
			log.info("Request from " + request.getOwner().getFirstName() + " " + request.getOwner().getLastName() + " deleted successfully.");
			return true;
		} else {
			log.info("Request was not deleted.");
			return false;
		}
	}

	public Request getRequest(Profile requestOwner) throws PoputchikDoesNotExistException {
		log.info("getRequest() started.");
		Request result = null;
		for (Request current : requests) {
			if (current.getOwner().equals(requestOwner)) {
				result = current;
				break;
			}
		}
		if (result == null) {
			log.error("Error in createRequest: Failed to get request: request from " + requestOwner.getFirstName() + " " + requestOwner.getLastName() + " does not exist.");
			throw new PoputchikDoesNotExistException("Failed to get request: request from " + requestOwner.getFirstName() + " " + requestOwner.getLastName() + " does not exist.");
		}
		log.info("getRequest() finished.");
		return result;
	}

	public List<Request> searchRequestsNotResponded() {
		log.info("searchRequestsNotResponded() started.");
		List<Request> result = new ArrayList<Request>();
		for (Request current : requests) {
			if (current.getStatus().equals("initial")) {
				result.add(current);
			}
		}
		log.info("searchRequestsNotResponded() finished.");
		return result;
	}

	public void confirmRequest(Profile requestOwner) {
		try {
			Request request = getRequest(requestOwner);
			request.setStatus(RequestStatus.confirmed);
		} catch (PoputchikDoesNotExistException e) {
			// TODO log.error("Failed to get request: request does not exist.", e);

		}
	}

	public void denyRequest(Profile requestOwner) {
		try {
			Request request = getRequest(requestOwner);
			request.setStatus(RequestStatus.denied);
		} catch (PoputchikDoesNotExistException e) {
			// TODO log.error("Failed to get request: request does not exist.", e);

		}
	}

	public Comment createComment(Profile owner, String body) {
		log.info("createComment() started.");
		Comment comment = new Comment();
		comment.setOwner(owner);
		comment.setBody(body);
		comment.setDateTime(new Date());
		comments.add(comment);
		log.info("New comment successfully created.");
		return comment;
	}

	/*
	 * public Request createRequest(Profile owner){ Request request = new Request(); request.setOwner(owner); request.setStatus("initial"); requests.add(request); return request; }
	 */

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

	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus active) {
		this.status = active;
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
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
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
