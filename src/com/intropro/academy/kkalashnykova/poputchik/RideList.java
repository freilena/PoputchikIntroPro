package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RideList {
	
	private List <Ride> rides = new ArrayList <Ride>();
	
	
	public Ride createRide(String start, String finish, Date dateTime, Profile owner){  //делигирование - один класс делигировал все полномочия другому классу
		if(this.search(start, finish, dateTime, owner).size() == 0){
			Ride ride = Ride.createRide(start, finish, dateTime, owner);
			rides.add(ride);
			return ride;
		}
		else{ 
			throw new PoputchikDomainRideAlreadyExistsException();
		}
	}
	
	//continue in foreach -  не выполнять дальше код из цикла, но взять следующую итерацию 
	
	public List <Ride> search(String start, String finish, Date dateTime, Profile owner){
		List <Ride> ridesFound = new ArrayList <Ride>();
		if(dateTime == null && owner == null && start == null && finish == null){ 
			ridesFound = rides;
		}
		else if(dateTime == null && owner != null && start != null && finish != null){ 
			for (Ride ride : rides){
				if(ride.getStart().equals(start) && ride.getFinish().equals(finish) && ride.getOwner().equals(owner) && !ride.getStatus().equals("expired")){
					ridesFound.add(ride);
				}
			}
		}
		else if(dateTime != null &&  owner == null && start != null && finish != null){ 
			for (Ride ride : rides){
				if(ride.getStart().equals(start) && ride.getFinish().equals(finish) && !ride.getStatus().equals("expired")){
					ridesFound.add(ride);
				}
			}
		}
		else if(dateTime == null && owner == null && start != null && finish != null){
			for (Ride ride : rides){
				if(ride.getStart().equals(start) && ride.getFinish().equals(finish) && !ride.getStatus().equals("expired")){
					ridesFound.add(ride);
				}
			}
		}
		else if(dateTime == null && owner == null && start != null && finish == null){ 
			for (Ride ride : rides){
				if(ride.getStart().equals(start) && !ride.getStatus().equals("expired")){
					ridesFound.add(ride);
				}
			}
		}
		else if(dateTime == null && owner == null && start == null && finish != null){ 
			for (Ride ride : rides){
				if(ride.getFinish().equals(finish) && !ride.getStatus().equals("expired")){
					ridesFound.add(ride);
				}
			}
		}
		else{//dateTime != null && owner != null && start != null && finish != null) 
			for (Ride ride : rides){
				if(ride.getStart().equals(start) && ride.getFinish().equals(finish) && ride.getDateTime().equals(dateTime) && ride.getOwner().equals(owner) && !ride.getStatus().equals("expired")){
					ridesFound.add(ride);
				}
			}
		}
		return ridesFound;
	}
	
	
	public boolean deleteRide(Ride ride){
		if(rides.contains(ride)){
			rides.remove(ride);
			return true;
		}
		else{
			//to do some code here
			return false;
		}
		//  Должен ли делит что-то возвращать?
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}
	
}