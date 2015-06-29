package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class RideTest {

	private Date date;
	private Profile owner;
	private Profile requestOwner;
	private Profile requestOwner2;
	private Profile requestOwner3;
	private RideList rides;
	private Ride ride;
	

	@Before
	public void setUp() throws Exception {
		date = new Date();

		owner = new Profile();
		requestOwner = new Profile();
		requestOwner.setFirstName("Test1");
		requestOwner2 = new Profile();
		requestOwner2.setFirstName("Test2");
		requestOwner3 = new Profile();
		requestOwner3.setFirstName("Test3");

		rides = new RideList();
		ride = rides.createRide("Cristall", "Grygorenka", date, owner);
	}

	@Test
	public void testCreateRequest() {
		try {
			Request request = ride.createRequest(requestOwner);
			assertNotNull(request);
			assertTrue(!ride.getRequests().isEmpty());
			assertEquals(request, ride.getRequests().get(0));
			assertEquals("initial", ride.getRequests().get(0).getStatus());

			ride.createRequest(requestOwner2);
			assertEquals(2, ride.getRequests().size());
		} catch (PoputchikAlreadyExistsException e) {
			fail("testCreateRequest() should not throw exeption");
		}

		try {
			ride.createRequest(requestOwner);
			fail("Should throw exeption");
		} catch (PoputchikAlreadyExistsException e) {
		}

	}

	@Test
	public void testDeleteRequest() {
		try {
			Request request = ride.createRequest(requestOwner);
			assertTrue(!ride.getRequests().isEmpty());
			ride.deleteRequest(request);
			assertTrue(ride.getRequests().isEmpty());
			assertTrue(!ride.deleteRequest(request));// check somehow that you can't delete same object two times and no uncaughted exceptions
		} catch (PoputchikAlreadyExistsException e) {
			fail("createRequest() should not throw exeption");
		}
	}

	@Test
	public void testGetRequest() {
		try {
			ride.createRequest(requestOwner2);
			Request request = ride.createRequest(requestOwner);

			assertEquals(request, ride.getRequest(requestOwner));
		} catch (PoputchikDoesNotExistException | PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}

		try {
			ride.getRequest(owner);
			fail("Should throw exeption");
		} catch (PoputchikDoesNotExistException e) {
		}
	}

	@Test
	public void testSearchRequestsNotResponded() {
		try {
			ride.createRequest(requestOwner);
			ride.createRequest(requestOwner2);

			assertEquals(2, ride.searchRequestsNotResponded().size());

			ride.getRequest(requestOwner2).setStatus(RequestStatus.confirmed);
			assertEquals(1, ride.searchRequestsNotResponded().size());

			ride.getRequest(requestOwner).setStatus(RequestStatus.denied);
			assertEquals(0, ride.searchRequestsNotResponded().size());
		} catch (PoputchikDoesNotExistException | PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}
	}

	@Test
	public void testCreateComment() {

		Comment comment = ride.createComment(owner, "My first comment");
		assertNotNull(comment);
		assertTrue(!ride.getComments().isEmpty());
		assertEquals(comment, ride.getComments().get(0));
		assertEquals(owner, ride.getComments().get(0).getOwner());
		assertEquals("My first comment", ride.getComments().get(0).getBody());
	}
}
