package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RideList {

	private List<Ride> rides = new ArrayList<Ride>();

	public Ride createRide(String start, String finish, Date dateTime,
			Profile owner) { // делигирование - один класс делигировал все
								// полномочия другому классу
		if (search(start, finish, dateTime, owner).size() != 0) {
			throw new PoputchikDomainObjectAlreadyExistsException();
		}
		Ride ride = Ride.createRide(start, finish, dateTime, owner);
		rides.add(ride);
		return ride;
	}

	// continue in foreach - не выполнять дальше код из цикла, но взять
	// следующую итерацию

	public List<Ride> search(String start, String finish,
			Date dateTime, Profile owner) {
		List<Ride> ridesFound = new ArrayList<Ride>();

		if (dateTime == null && owner == null && start == null
				&& finish == null) {
			ridesFound = rides;
		} else if (dateTime == null && owner != null && start != null
				&& finish != null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start)
						&& ride.getFinish().equals(finish)
						&& ride.getOwner().equals(owner)
						&& !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime != null && owner == null && start != null
				&& finish != null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start)
						&& ride.getFinish().equals(finish)
						&& !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime == null && owner == null && start != null
				&& finish != null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start)
						&& ride.getFinish().equals(finish)
						&& !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime == null && owner == null && start != null
				&& finish == null) {
			for (Ride ride : rides) {
				if (ride.getStart().equals(start)
						&& !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else if (dateTime == null && owner == null && start == null
				&& finish != null) {
			for (Ride ride : rides) {
				if (ride.getFinish().equals(finish)
						&& !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		} else {// dateTime != null && owner != null && start != null && finish
				// != null)
			for (Ride ride : rides) {
				if (ride.getStart().equals(start)
						&& ride.getFinish().equals(finish)
						&& ride.getDateTime().equals(dateTime)
						&& ride.getOwner().equals(owner)
						&& !ride.getStatus().equals("expired")) {
					ridesFound.add(ride);
				}
			}
		}
		return ridesFound;
	}

	public List<Ride> search(Map searchParameters) {
		boolean firstRide = true;
		List<Ride> ridesFound = new ArrayList<Ride>();
		/*
		 * for (Object key : searchParameters.keySet()) { for (SearchParameters
		 * parameter : SearchParameters.values()) { if
		 * (key.equals(parameter.toString())) { for (Ride ride : rides) { if
		 * (firstRide) { if (ride.getStart().equals( searchParameters.get(key)))
		 * { ridesFound.add(ride); firstRide = false; } } else { if
		 * (!ridesFound.contains(ride)) { if (ride.getStart().equals(
		 * searchParameters.get(key))) { ridesFound.add(ride); } } } } } } }
		 */
		for (Object key : searchParameters.keySet()) {
			if (key.equals(SearchParameters.start.toString())) {
				for (Ride ride : rides) {
					if (firstRide) {
						if (ride.getStart().equals(searchParameters.get(key))) {
							ridesFound.add(ride);
							firstRide = false;
						}
					} else {
						if (!ridesFound.contains(ride)) {
							if (ride.getStart().equals(
									searchParameters.get(key))) {
								ridesFound.add(ride);
							}
						}
					}
				}
			} else if (key.equals(SearchParameters.finish.toString())) {
				for (Ride ride : rides) {
					if (firstRide) {
						if (ride.getFinish().equals(searchParameters.get(key))) {
							ridesFound.add(ride);
							firstRide = false;
						}
					} else {
						if (!ridesFound.contains(ride)) {
							if (ride.getFinish().equals(
									searchParameters.get(key))) {
								ridesFound.add(ride);
							}
						}
					}
				}
			} else if (key.equals(SearchParameters.datetime.toString())) {
				for (Ride ride : rides) {
					if (firstRide) {
						if (ride.getDateTime().equals(searchParameters.get(key))) {
							ridesFound.add(ride);
							firstRide = false;
						}
					} else {
						if (!ridesFound.contains(ride)) {
							if (ride.getDateTime().equals(
									searchParameters.get(key))) {
								ridesFound.add(ride);
							}
						}
					}
				}
			} else if (key.equals(SearchParameters.owner.toString())) {
				for (Ride ride : rides) {
					if (firstRide) {
						if (ride.getOwner().equals(searchParameters.get(key))) {
							ridesFound.add(ride);
							firstRide = false;
						}
					} else {
						if (!ridesFound.contains(ride)) {
							if (ride.getOwner().equals(
									searchParameters.get(key))) {
								ridesFound.add(ride);
							}
						}
					}
				}
			}
		}
		return ridesFound;
	}

	public boolean deleteRide(Ride ride) {
		if (rides.contains(ride)) {
			rides.remove(ride);
			return true;
		} else {
			// to do some code here
			return false;
		}
		// Должен ли делит что-то возвращать?
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