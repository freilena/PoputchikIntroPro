package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class RideListTest {

	private Date date;
	private Date date2;
	private Profile owner;
	private Profile owner2;
	private Profile owner3;
	private RideList rides;

	@Before
	public void setUp() throws Exception {
		date = new Date();
		date2 = new Date();

		owner = new Profile();
		owner.setFirstName("Test1");
		owner2 = new Profile();
		owner2.setFirstName("Test2");
		owner3 = new Profile();
		owner3.setFirstName("Test3");

		rides = new RideList();
	}

	@Test
	public void testCreateRide() {

		// check add one
		try {
			Ride ride = rides.createRide("Cristall", "Grygorenka", date, owner);

			assertNotNull(ride);
			assertTrue(!rides.getRides().isEmpty());
			assertEquals(ride, rides.getRides().get(0));

			// check add two
			Ride ride2 = rides.createRide("Grygorenka", "Cristall", date, owner);
			assertEquals(rides.getRides().size(), 2);
			assertEquals(ride2, rides.getRides().get(1));
		} catch (PoputchikAlreadyExistsException e) {
			fail("testCreateRide Should not throw exeption");
		}

		// check add already existed
		try {
			rides.createRide("Grygorenka", "Cristall", date, owner);
			fail("Should throw exeption");
		} catch (PoputchikAlreadyExistsException e) {
		}
		assertEquals(2, rides.getRides().size());

	}

	@Test
	public void testDeleteRide() {

		try {
			Ride ride = rides.createRide("Cristall", "Grygorenka", date, owner);
			assertTrue(!rides.getRides().isEmpty());
			assertTrue(rides.deleteRide(ride));
			assertTrue(rides.getRides().isEmpty());

			assertTrue(!rides.deleteRide(ride));// check somehow that you can't delete same object two times and no uncaughted exceptions
		} catch (PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}
	}

	@Test
	public void testSearchRide() {

		try {
			rides.createRide("Cristall", "Grygorenka", date, owner);
		} catch (PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}
		
		try {
			rides.createRide("Grishka", "Cristall", date, owner);
		} catch (PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}

		try {
			rides.createRide("Cristall", "Grygorenka", date2, owner2);
		} catch (PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}
		
		try {
			rides.createRide("Grygorenka", "Cristall", date, owner);
		} catch (PoputchikAlreadyExistsException e) {
			fail("Should not throw exeption");
		}

		assertTrue(!rides.getRides().isEmpty());

		assertEquals(1, rides.search("Grishka", "Cristall", date, owner).size());
		assertEquals(1, rides.search("Cristall", "Grygorenka", null, owner2).size());
		//assertEquals(1, rides.search("Cristall", "Grygorenka", date2, null).size()); //fails because dates are same
		
		//if dateTime == null && owner == null && start != null && finish != null)
		assertEquals(2, rides.search("Cristall", "Grygorenka", null, null).size());

		assertEquals(2, rides.search(null, "Grygorenka", null, null).size());
		assertEquals(0, rides.search("A", "Cristall", date, owner).size());
		assertEquals(0, rides.search("Grishka", "B", date, owner).size());
		//assertEquals(0, rides.search("Grishka", "Cristall", date2, owner).size()); //fails because dates are same
		assertEquals(0, rides.search("Grishka", "Cristall", date, owner3).size());
		
		//if dateTime == null && owner == null && start == null && finish == null
		assertEquals(rides.getRides().size(), rides.search(null, null, null, null).size());
		

		
		
	}

}