package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RideTest {
	
	@Test
	public void testCreateRequest(){
		Date date = new Date();
		Profile owner = new Profile();
		Profile requestOwner = new Profile();
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);
		
		Request request = ride.createRequest(requestOwner);
		assertNotNull(request);
		assertTrue(!ride.getRequests().isEmpty());
		assertEquals(request, ride.getRequests().get(0));
		assertEquals("initial", ride.getRequests().get(0).getStatus());
	}
	
	@Test
	public void testDeleteRequest(){
		Date date = new Date();
		Profile owner = new Profile();
		Profile requestOwner = new Profile();
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);
		
		Request request = ride.createRequest(requestOwner);
		assertTrue(!ride.getRequests().isEmpty());
		ride.deleteRequest(request);
		assertTrue(ride.getRequests().isEmpty());
		assertTrue(!ride.deleteRequest(request));// check somehow that you can't delete same object two times and no uncaughted exceptions
		
	}
	
	@Test
	public void testSearchRequest(){
		
		Date date = new Date();
		Profile owner = new Profile();
		Profile requestOwner = new Profile();
		requestOwner.setFirstName("Test1");
		Profile requestOwner2 = new Profile();
		requestOwner2.setFirstName("Test2");
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);
		ride.createRequest(requestOwner2);
		Request request = ride.createRequest(requestOwner);
	
		assertEquals(request, ride.searchRequest(requestOwner));
		
		try{
			assertEquals(request, ride.searchRequest(owner));
		}
		catch(PoputchikDomainObjectDoesnotExistException e){
			assertNotNull(e);
		}
	}
}























