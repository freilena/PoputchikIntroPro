package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RideTest {

	@Test
	public void testCreateRequest() {
		Date date = new Date();
		Profile owner = new Profile();
		Profile requestOwner = new Profile();
		requestOwner.setFirstName("Test1");
		Profile requestOwner2 = new Profile();
		requestOwner.setFirstName("Test2");
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);

		Request request = ride.createRequest(requestOwner);
		assertNotNull(request);
		assertTrue(!ride.getRequests().isEmpty());
		assertEquals(request, ride.getRequests().get(0));
		assertEquals("initial", ride.getRequests().get(0).getStatus());

		try {
			ride.createRequest(requestOwner);
			fail("Should throw exeption");
		} catch (PoputchikDomainObjectAlreadyExistsException e) {
		}

		ride.createRequest(requestOwner2);
		assertEquals(2, ride.getRequests().size());
	}

	@Test
	public void testDeleteRequest() {
		Date date = new Date();
		Profile owner = new Profile();
		Profile requestOwner = new Profile();
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);

		Request request = ride.createRequest(requestOwner);
		assertTrue(!ride.getRequests().isEmpty());
		ride.deleteRequest(request);
		assertTrue(ride.getRequests().isEmpty());
		assertTrue(!ride.deleteRequest(request));// check somehow that you can't
													// delete same object two
													// times and no uncaughted
													// exceptions

	}

	@Test
	public void testGetRequest() {

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

		assertEquals(request, ride.getRequest(requestOwner));

		try {
			ride.getRequest(owner);
			fail("Should throw exeption");
		} catch (PoputchikDomainObjectDoesnotExistException e) {
		}
	}

	@Test
	public void testSearchRequestsNotResponded() {
		Date date = new Date();
		Profile owner = new Profile();
		Profile requestOwner = new Profile();
		requestOwner.setFirstName("Test1");
		Profile requestOwner2 = new Profile();
		requestOwner2.setFirstName("Test2");
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);
		ride.createRequest(requestOwner);
		ride.createRequest(requestOwner2);

		assertEquals(2, ride.searchRequestsNotResponded().size());
		
		ride.getRequest(requestOwner2).setStatus("confirmed");
		assertEquals(1, ride.searchRequestsNotResponded().size());
		
		ride.getRequest(requestOwner).setStatus("denied");
		assertEquals(0, ride.searchRequestsNotResponded().size());
	}

	@Test
	public void testCreateComment() {
		Date date = new Date();
		Profile owner = new Profile();
		RideList rideList = new RideList();
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);

		Comment comment = ride.createComment(owner, "My first comment");
		assertNotNull(comment);
		assertTrue(!ride.getComments().isEmpty());
		assertEquals(comment, ride.getComments().get(0));
		assertEquals(owner, ride.getComments().get(0).getOwner());
		assertEquals("My first comment", ride.getComments().get(0).getBody());
	}

}
