package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.*;

public class RideList {

	private List<Ride> rides = new ArrayList<Ride>();
	private static Logger log = LogManager.getLogger(RideList.class);

	public Ride createRide(String start, String finish, Date dateTime, Profile owner) throws PoputchikAlreadyExistsException {
		log.info("createRideList started.");
		if (search(start, finish, dateTime, owner).size() != 0) {
			log.error("Error in createRide: Failed to create new ride: Ride already exist.");
			throw new PoputchikAlreadyExistsException("Failed to create new ride: Ride already exist.");
		}
		Ride ride = Ride.createRide(start, finish, dateTime, owner);
		rides.add(ride);
		RideDao.createRide(ride);
		log.info("createRideList finished.");
		return ride;
	}

	public List<Ride> search(String start, String finish, Date dateTime, Profile owner) {
		log.info("search Ride started.");
		List<Ride> ridesFound = new ArrayList<Ride>();

		if (dateTime == null && owner == null && start == null && finish == null) {
			ridesFound = rides;
		} else if (dateTime == null && owner != null && start != null && finish != null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start) && ride.getFinish().equals(finish) && ride.getOwner().equals(owner) && !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime != null && owner == null && start != null && finish != null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start) && ride.getFinish().equals(finish) && !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime == null && owner == null && start != null && finish != null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start) && ride.getFinish().equals(finish) && !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime == null && owner == null && start != null && finish == null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start) && !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime == null && owner == null && start == null && finish != null) {
			for (Ride ride : rides) {
				if (ride.getFinish().equals(finish) && !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start) && ride.getFinish().equals(finish) && ride.getDateTime().equals(dateTime) && ride.getOwner().equals(owner) && !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		}
		log.info("search Ride finished.");
		return ridesFound;
	}

	public boolean deleteRide(Ride ride) {
		if (rides.contains(ride)) {
			rides.remove(ride);
			log.info("Ride successfully deleted.");
			return true;
		} else {
			// TODO some code here
			log.info("Ride was not deleted.");
			return false;
		}
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rides == null) ? 0 : rides.hashCode());
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
		RideList other = (RideList) obj;
		if (rides == null) {
			if (other.rides != null)
				return false;
		} else if (!rides.equals(other.rides))
			return false;
		return true;
	}

}