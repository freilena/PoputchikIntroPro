package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {
	
	private Date date;
	private Profile rideOwner;
	private Profile requestOwner;
	private RideList rides;
	private Ride ride;

	@Before
	public void setUp() throws Exception {
		date = new Date();

		rideOwner = new Profile();
		requestOwner = new Profile();
		requestOwner.setFirstName("Test1");

		rides = new RideList();
		ride = rides.createRide("Cristall", "Grygorenka", date, rideOwner);
	}
	
	@Test
	public void testSmoke() {
		Request request = new Request();
		assertNotNull(request);
	}

	/*
	 * @Test public void testNotifyRequest(){ fail("Not implemented yet"); }
	 */
/*
	@Test
	public void testConfirmRequest() {
		try {
			Ride ride = rideList.createRide("Cristall", "Grygorenka", date, rideOwner);

			Request request = ride.createRequest(rideOwner);
			assertEquals("initial", ride.getRequests().get(0).getStatus());
			
			 ride.getRequest(requestOwner).setStatus("confirmed"); 
			 assertEquals("confirmed",ride.getRequest(requestOwner).getStatus());
			 
		} catch (PoputchikAlreadyExistsException e) {
			fail("testConfirmRequest() should not throw exeption");
		}
	}*/

	/*
	 * @Test public void testDenyRequest(){ fail("Not implemented yet"); }
	 */
}
