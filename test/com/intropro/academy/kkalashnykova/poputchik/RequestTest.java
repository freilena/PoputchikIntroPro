package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class RequestTest {

	@Test
	public void testSmoke() {
		Request request = new Request();
		assertNotNull(request);
	}
	
	/*
	@Test
	public void testNotifyRequest(){
		fail("Not implemented yet");
	}
	*/
	
	@Test
	public void testConfirmRequest(){
		Date date = new Date();
		Profile rideOwner = new Profile();
		Profile requestOwner = new Profile();
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, rideOwner);
		
		Request request = ride.createRequest(rideOwner);
		assertEquals("initial", ride.getRequests().get(0).getStatus());
		/*ride.searchRequest(requestOwner).setStatus("confirmed");
		assertEquals("confirmed",ride.searchRequest(ride, requestOwner).getStatus());*/
	}
	
	/*
	@Test
	public void testDenyRequest(){
		fail("Not implemented yet");
	}
	*/
}
